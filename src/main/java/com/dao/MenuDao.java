package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Menu;

import util.DbCon;

public class MenuDao {

	public List<Menu> getlist() throws SQLException {

		Connection con = DbCon.getConnection();
		Menu m = null;
		List<Menu> list = new ArrayList<Menu>();
		try {
			PreparedStatement pst = con.prepareStatement(
					"select MENU_CODE,MENU_DESC,MENU_URL,PARENT_MENU,STATUS_TYPE, nvl(module_type,'.') MODULE_TYPE from WEB_MENU_ENTRY ORDER BY MENU_CODE, MENU_DESC");
			ResultSet rs = pst.executeQuery();
			int i = 1;
			while (rs.next()) {

				m = new Menu();

				m.setSN(i);
				m.setMENU_CODE(rs.getString("MENU_CODE"));
				m.setMENU_DESC(rs.getString("MENU_DESC"));
				m.setMENU_URL(rs.getString("MENU_URL"));
				m.setPARENT_MENU(rs.getString("PARENT_MENU"));
				m.setSTATUS_TYPE(rs.getString("STATUS_TYPE"));
				m.setMODULE_TYPE(rs.getString("MODULE_TYPE"));
				list.add(m);
				i = i + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return list;
	}

	public String saveMenu(Menu m) throws SQLException {
		Connection con = DbCon.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(
					"insert into WEB_MENU_ENTRY(MENU_CODE,MENU_DESC,CREATE_BY,CREATE_DATE,MENU_URL,PARENT_MENU,STATUS_TYPE,MODULE_TYPE)values(?,?,?,sysdate,?,?,?,?)");
			pst.setString(1, m.getMENU_CODE());
			pst.setString(2, m.getMENU_DESC());
			pst.setString(3, m.getUSER());
			pst.setString(4, m.getMENU_URL());
			pst.setString(5, m.getPARENT_MENU());
			pst.setString(6, m.getSTATUS_TYPE());
			pst.setString(7, m.getMODULE_TYPE());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to Save : " + e.getMessage();
		} finally {
			con.close();
		}
		return "Successfully saved!";
	}

	public String update(Menu a) throws SQLException {
		Connection con = DbCon.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(
					"update WEB_MENU_ENTRY set MENU_DESC=?,MENU_URL=?,PARENT_MENU=?,STATUS_TYPE=?, MODULE_TYPE=? where MENU_CODE=?");
			pst.setString(1, a.getMENU_DESC());
			pst.setString(2, a.getMENU_URL());
			pst.setString(3, a.getPARENT_MENU());
			pst.setString(4, a.getSTATUS_TYPE());
			pst.setString(5, a.getMODULE_TYPE());
			pst.setString(6, a.getMENU_CODE());
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to Update : " + e.getMessage();
		} finally {
			con.close();
		}
		return "Successfully updated!";
	}

	public String delete(String code) throws SQLException {
		Connection con = DbCon.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("delete from WEB_MENU_ENTRY where MENU_CODE=?");
			pst.setString(1, code);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to Delete : " + e.getMessage();
		} finally {
			con.close();
		}
		return "Successfully deleted!";
	}

	public List<Menu> getByParent() throws SQLException {
		Connection con = DbCon.getConnection();
		Menu m = null;
		List<Menu> list = new ArrayList<Menu>();
		try {
			PreparedStatement pst = con.prepareStatement("SELECT    LEVEL, MENU_CODE,\n"
					+ "              SUBSTR (LPAD ('  ', 8 * (LEVEL - 1)) || MENU_DESC, 1,\n"
					+ "                      50) MENU_DESC,\n" + "                    \n"
					+ "                      PARENT_MENU,\n" + "                      MENU_URL\n"
					+ "         FROM WEB_MENU_ENTRY\n" + "    START WITH PARENT_MENU IS NULL\n"
					+ "   CONNECT BY PRIOR MENU_CODE = PARENT_MENU\n" + "     ORDER BY MENU_CODE, MENU_DESC");
			ResultSet rs = pst.executeQuery();
			int i = 1;
			while (rs.next()) {
				m = new Menu();
				m.setSN(i);
				m.setLEVEL(rs.getString("LEVEL"));
				m.setMENU_CODE(rs.getString("MENU_CODE").trim());
				m.setMENU_DESC(rs.getString("MENU_DESC"));
				m.setMENU_URL(rs.getString("MENU_URL"));
				m.setPARENT_MENU(rs.getString("PARENT_MENU"));
				list.add(m);
				i = i + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return list;
	}

}
