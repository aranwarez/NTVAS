package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbCon {
	  public static Connection getConnection() {
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "USERNAME", "PASSWORD");
	            
	            return con;
	        } catch (Exception e) {


	            e.printStackTrace();

	        }
	        return null;
	    }
}
