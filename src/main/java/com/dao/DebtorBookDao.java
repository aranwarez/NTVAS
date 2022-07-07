package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DbCon;

public class DebtorBookDao {

	public String SaveBill(List<Map<String, Object>> myarray, String SP_CODE, String nepdate,
			String REMARKS,  String USER, String CC_CODE) throws SQLException {
		Connection con = DbCon.getConnection();

		try {
			String transno = null;
			con.setAutoCommit(false);
			String qry1 = "SELECT COMMON.GET_FISCAL_YEAR(?) || lpad(SEQ_DEBTORBOOK_TRANS_NO.nextval,6,'0') FROM dual";
			PreparedStatement prs1 = con.prepareStatement(qry1);
			prs1.setString(1, nepdate);
			ResultSet rs1 = prs1.executeQuery();
			if (rs1.next()) {
				transno = rs1.getString(1);
			}
			PreparedStatement pst1 = con.prepareStatement("INSERT INTO DEBTOR_BOOK_MASTER\r\n"
					+ "(CC_CODE, TRANS_NO, SALES_DATE, CUSTOMER_CODE, FISAL_YEAR,"
					+ " REMARKS, POST_FLAG,CREATE_BY, POST_BY, POST_DT)\r\n"
					+ "VALUES(?,?,common.to_ad(?),?,COMMON.GET_FISCAL_YEAR(?),?,'Y',?,?,sysdate)");

			pst1.setString(1, CC_CODE);
			pst1.setString(2, transno);
			pst1.setString(3, nepdate);
			pst1.setString(4, SP_CODE);
			pst1.setString(5, nepdate);
			//pst1.setString(6, BANK_CODE);
			pst1.setString(6, REMARKS);
			pst1.setString(7, USER);
			pst1.setString(8, USER);

			pst1.executeUpdate();

			for (Map<String, Object> obj : myarray) {
				PreparedStatement pst4 = con.prepareStatement("INSERT INTO DEBTOR_BOOK_DETAIL\r\n"
						+ "(TRANS_ID, CC_CODE, TRANS_NO, ITEM_CODE, QTY, RATE, REVENUE_AMT, TSC_AMT, VAT_AMT,OWT_AMT, CREATE_BY)\r\n"
						+ "VALUES(SEQ_DEBTORBOOK_TRANS_ID.nextval,?,?,?,?,?,?,?,?,?,?)");

				pst4.setString(1, CC_CODE);
				pst4.setString(2, transno);
				pst4.setString(3, (String) obj.get("ITEM_CODE"));
				pst4.setString(4, (String) obj.get("QUANTITY"));
				pst4.setString(5, (String) obj.get("RATE"));
				pst4.setString(6, (String) obj.get("REV"));
				pst4.setString(7, (String) obj.get("TSC"));
				pst4.setString(8, (String) obj.get("VAT"));
                                
                                pst4.setString(9, (String) obj.get("OWN"));
				pst4.setString(10, USER);

				pst4.executeUpdate();
			}

			con.commit();

			return "Sucessfully saved data for transaction :"+transno;

		} catch (SQLException ex) {
			con.rollback();
			ex.printStackTrace();
			return "Failed " + ex;
		} finally {

			con.close();

		}

	}

//For Master 
	public List<Map<String, Object>> getReceiptList(String S_NO, String FROM_DT, String TO_DT, String POST_FLAG)
			throws SQLException {
		Connection con = DbCon.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(
					"select a.*,common.to_bs(a.sales_date) NP_Sales_date ,common.to_bs(a.CANCEL_DT) CANCEL_DT ,common.to_bs(a.sales_date) NP_Create_dt,(select SP_NAME from m_sp where sp_code=a.CUSTOMER_CODE) CUSTOMER_CODE,(SELECT SUM (\r\n" + 
					"             NVL (REVENUE_AMT, 0)\r\n" + 
					"           + NVL (TSC_AMT, 0)\r\n" + 
					"           + NVL (VAT_AMT, 0)\r\n" + 
					"           + NVL (OWT_AMT, 0)\r\n" + 
					"           - NVL (DISC, 0))\r\n" + 
					"  FROM DEBTOR_BOOK_DETAIL\r\n" + 
					" WHERE trans_no =a.trans_no) TOTALAMT from DEBTOR_BOOK_MASTER a where sales_date BETWEEN nvl(common.to_ad(?),sysdate-30) AND nvl(common.to_ad(?),sysdate)\r\n"
							+ "                    AND CUSTOMER_CODE =nvl(?,CUSTOMER_CODE)\r\n"
							+ "                    AND post_flag=nvl(?,post_flag)\r\n"
							+ "                    ORDER BY trans_NO");

			pst.setString(1, FROM_DT);
			pst.setString(2, TO_DT);
			pst.setString(3, S_NO);
			pst.setString(4, POST_FLAG);
			ResultSet rs = pst.executeQuery();

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = rs.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (rs.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), rs.getObject(i));
				}
				resultList.add(row);
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}
//gettting detail for master
	public List<Map<String, Object>> getDetailList(String transno)
			throws SQLException {
		Connection con = DbCon.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(
					"select a.*,(select description from m_item where item_code=a.item_code) DITEM from DEBTOR_BOOK_DETAIL a where trans_no=?");

			pst.setString(1, transno);
			
			ResultSet rs = pst.executeQuery();

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			Map<String, Object> row = null;

			ResultSetMetaData metaData = rs.getMetaData();
			Integer columnCount = metaData.getColumnCount();

			while (rs.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), rs.getObject(i));
				}
				resultList.add(row);
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return null;
	}

	
	
	
	public String DeleteReceipt(String TRANSNO, String USER) throws SQLException {
		Connection con = DbCon.getConnection();
		try {
			String qry = "UPDATE DEBTOR_BOOK_MASTER SET POST_flag='C', cancel_dt=sysdate, cancel_by=? WHERE trans_no=?";

			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, USER);
			pst.setString(2, TRANSNO);
			pst.executeUpdate();
			return "Succesfully Cancel Bill Transaction";
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			return "Failed to cancel : " + e.getMessage();
		} finally {
			con.close();
		}
	}
	
	
	
	 public Map<String, Object> getSpdue(String SP_CODE, String ITEM_CODE) throws SQLException {
	        Connection con = DbCon.getConnection();
	        try {
	            PreparedStatement pst = con.prepareStatement("SELECT sum(nvl(amt,0)) payable_amt, sum(nvl(royalty,0)) royalty,nvl(sum(nvl(amt,0)-nvl(royalty,0)),0) payable_before_tax, sum(nvl(tsc_amt,0)), sum(nvl(vat_amt,0)), nvl(sum(total_amt),0) bal_amt_with_tax FROM vw_ledger\n"
	                    + "WHERE s_no=? AND item_code=?\n"
	                    + "AND sharing_type='N' AND post_flag='Y'");
	            pst.setString(1, SP_CODE);
	            pst.setString(2, ITEM_CODE);
	            ResultSet rs = pst.executeQuery();

	            Map<String, Object> row = null;

	            ResultSetMetaData metaData = rs.getMetaData();
	            Integer columnCount = metaData.getColumnCount();
	            while (rs.next()) {
	                row = new HashMap<String, Object>();

	                for (int i = 1; i <= columnCount; i++) {
	                    row.put(metaData.getColumnName(i), rs.getObject(i));
	                }

	            }
	            return row;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            con.close();
	        }
	        return null;
	    }


}
