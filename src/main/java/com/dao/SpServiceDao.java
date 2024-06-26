/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author nabin
 */
public class SpServiceDao {

    public List<Map<String, Object>> getSpServiceList(String SP_CODE) throws SQLException {
        Connection con = DbCon.getConnection();

        try {
        	String qry="SELECT M.TRANS_ID, M.SP_CODE, A.SP_NAME, M.SERVICE_CODE, B.DESCRIPTION SERVICE,\r\n" + 
        			"   M.ACTIVE_FLAG, M.ACTIVE_DT, M.DEACTIVATE_DT, COMMON.TO_BS(M.ACTIVE_DT) NEP_ACTIVE_DT, COMMON.TO_BS(M.DEACTIVATE_DT) NEP_DEACTIVE_DT,\r\n" + 
        			"   M.CREATE_BY, M.CREATE_DT, M.UPDATE_BY, \r\n" + 
        			"   M.UPDATE_DT,contract_no, COMMON.TO_BS(proposal_receipt_dt) proposal_receipt_dt,COMMON.TO_BS(testing_letter_dt) testing_letter_dt,COMMON.TO_BS(test_report_receipt_dt) test_report_receipt_dt,COMMON.TO_BS(contract_valid_dt) contract_valid_dt,COMMON.TO_BS(contract_terminate_dt) contract_terminate_dt \r\n" + 
        			"FROM M_SP_SERVICE M, M_SP A, M_VAS_SERVICE B\r\n" + 
        			"WHERE A.SP_CODE=M.SP_CODE\r\n" + 
        			"AND B.SERVICE_CODE=M.SERVICE_CODE\r\n" + 
        			"AND M.SP_CODE=?\r\n" + 
        			"ORDER BY 3";
            PreparedStatement pst = con.prepareStatement(qry);
            System.out.println(qry);
            pst.setString(1, SP_CODE);
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

    public String saveSpService(String SP_CODE, String SERVICE_CODE, String ACTIVE_FLAG, String ACTIVE_DT, String DEACTIVATE_DT, String USER,String ContractNo,String proposal_receipt_dt,String testing_letter_dt,
            String test_report_receipt_dt,String contract_valid_dt,String contract_terminate_dt) throws SQLException {
        Connection con = DbCon.getConnection();
        try {

            String qry = "INSERT INTO M_SP_SERVICE (\n"
                    + "   TRANS_ID, SP_CODE, SERVICE_CODE, \n"
                    + "   ACTIVE_FLAG, ACTIVE_DT, DEACTIVATE_DT, \n"
                    + "   CREATE_BY, CREATE_DT,contract_no, proposal_receipt_dt,testing_letter_dt,test_report_receipt_dt,contract_valid_dt,contract_terminate_dt ) \n"
                    + "VALUES (SEQ_SPSERVICE_TRANSID.NEXTVAL , ?, ?,\n"
                    + "    ?, common.to_ad(?), common.to_ad(?),\n"
                    + "    ?, sysdate,?,common.to_ad(?),common.to_ad(?),common.to_ad(?),common.to_ad(?),common.to_ad(?))";

            PreparedStatement pst = con.prepareStatement(qry);
            pst.setString(1, SP_CODE);
            pst.setString(2, SERVICE_CODE);
            pst.setString(3, ACTIVE_FLAG);
            pst.setString(4, ACTIVE_DT);
            pst.setString(5, DEACTIVATE_DT);
            pst.setString(6, USER);
            pst.setString(7, ContractNo);
            pst.setString(8, proposal_receipt_dt);
            pst.setString(9, testing_letter_dt);
            pst.setString(10, test_report_receipt_dt);
            pst.setString(11, contract_valid_dt);
            pst.setString(12, contract_terminate_dt);
            

            pst.executeUpdate();

            return "Succesfully Saved Service Provider Services";

        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            return "Failed to Save : " + e.getMessage();
        } finally {
            con.close();
        }
    }

    public String updateSpService(String TRANS_ID, String SP_CODE, String SERVICE_CODE, String ACTIVE_FLAG, String ACTIVE_DT, String DEACTIVATE_DT, String USER,String ContractNo,String proposal_receipt_dt,String testing_letter_dt,
            String test_report_receipt_dt,String contract_valid_dt,String contract_terminate_dt) throws SQLException {
        Connection con = DbCon.getConnection();
        try {

            String qry = "UPDATE M_SP_SERVICE\n"
                    + "SET    SP_CODE       = ?, SERVICE_CODE  = ?,   ACTIVE_FLAG   = ?,\n"
                    + "       ACTIVE_DT     = common.to_ad(?), DEACTIVATE_DT = common.to_ad(?),  UPDATE_BY     = ?, UPDATE_DT     = SYSDATE,"
                    + "contract_no=?, proposal_receipt_dt=common.to_ad(?),testing_letter_dt=common.to_ad(?),test_report_receipt_dt=common.to_ad(?),"
                    + "contract_valid_dt=common.to_ad(?),contract_terminate_dt=common.to_ad(?)\n"
                    + "WHERE  TRANS_ID      = ?";

            PreparedStatement pst = con.prepareStatement(qry);
         //   System.out.println(qry);
            pst.setString(1, SP_CODE);
            pst.setString(2, SERVICE_CODE);
            pst.setString(3, ACTIVE_FLAG);
            pst.setString(4, ACTIVE_DT);
            pst.setString(5, DEACTIVATE_DT);
            pst.setString(6, USER);
            
            pst.setString(7, ContractNo);
            pst.setString(8, proposal_receipt_dt);
            pst.setString(9, testing_letter_dt);
            pst.setString(10, test_report_receipt_dt);
            pst.setString(11, contract_valid_dt);
            pst.setString(12, contract_terminate_dt);
            
            
            pst.setString(13, TRANS_ID);

            pst.executeUpdate();

            return "Succesfully Updated";

        } catch (Exception e) {
         //   con.rollback();
            e.printStackTrace();
            return "Failed to update : " + e.getMessage();
        } finally {
            con.close();
        }
    }

    public String DeleteSpService(String TRANS_ID) throws SQLException {
        Connection con = DbCon.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("delete from M_SP_SERVICE where TRANS_ID=?");
            pst.setString(1, TRANS_ID);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed Reason :" + e.getLocalizedMessage();
        } finally {
            con.close();
        }
        return "Record deleted successfully.";
    }
}
