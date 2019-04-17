package main.java.sqlVault;

import oracle.jdbc.OracleDriver;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.*;

public class SqlVaultDAO {

	private static Connection con = null;
	
	public static void initialSetup() {
		try{
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		con = DriverManager.getConnection("jdbc:oracle:thin:localhost:3306:orcl", "root", "");
		DatabaseMetaData d = con.getMetaData();
		ResultSet rs = d.getTables(null, null, "PW", null);
		if(rs.next()) {
			//table exists
			Statement s = con.createStatement();
			rs = s.executeQuery("SELECT * from PW");
			if(!rs.next()) {
				s = con.createStatement();
				s.executeUpdate("INSERT INTO PW (pass, flag) VALUES ('a_password_which_should_be_changed','CTF{trick_the_vault'");
			}
		}else {
			Statement s = con.createStatement();
			s.executeUpdate("CREATE TABLE PW (pass VARCHAR(255), flag VARCHAR(255))");
			s = con.createStatement();
			s.executeUpdate("INSERT INTO PW (pass, flag) VALUES ('password','CTF{trick_the_vault'");
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static String checkPW(String pass) {
		try{
			if(con != null) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM PW WHERE pass="+pass);
			if(rs.next()) {
				return rs.getString(1);
			}
		}else {
			initialSetup();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM PW WHERE pass="+pass);
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		return "Access Denied";
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
		return "Access Denied";
		}
		
	}
	
	public static boolean elementExists() {
		try{

		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from PW");
		return rs.next();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			return false;
		}
	}
}
