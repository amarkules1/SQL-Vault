package sqlVault;

//import com.mysql.jdbc.OracleDriver;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.*;

public class SqlVaultDAO {

	private static Connection con = null;

	//change these for connection and challenge purposes
	static String url = "jdbc:mysql://localhost:3306/test";
	static String user = "root";
	static String dbPass = "";
	static String vaultPass = "a_password_which_should_be_changed";
	static String flag = "CTF{trick_the_vault}";
	
	public static void initialSetup() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, dbPass);
		DatabaseMetaData d = con.getMetaData();
		ResultSet rs = d.getTables(null, null, "PW", null);
		if(rs.next()) {
			//table exists
			Statement s = con.createStatement();
			ResultSet rs1 = s.executeQuery("SELECT * from `PW`");
			
			if(!rs1.next()) {
				System.out.println(rs1.getString(0));
				s = con.createStatement();
				s.executeUpdate("INSERT INTO `PW` (`pass`,`flag`) VALUES ('"+vaultPass+"','"+flag+"')");
			}
			System.out.println(rs1.getString(1));
		}else {
			Statement s = con.createStatement();
			s.executeUpdate("CREATE TABLE PW (pass VARCHAR(255), flag VARCHAR(255))");
			s = con.createStatement();
				s.executeUpdate("INSERT INTO `PW` (`pass`,`flag`) VALUES ('"+vaultPass+"','"+flag+"')");
		}
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String checkPW(String maybe) {
		System.out.println(maybe);
		try{
			if(con != null) {
			Statement s = con.createStatement();
			//ResultSet rs = s.executeQuery("SELECT * FROM PW WHERE pass='' OR ''=''");
			ResultSet rs2 = s.executeQuery("SELECT * from `PW` WHERE pass='"+maybe+"'");
			if(rs2.next()) {
				System.out.println(rs2.getString(2));
				return rs2.getString(2);
			}
		}else {
			initialSetup();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from `PW` WHERE pass='"+maybe+"'");
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		return "Access Denied";
		}catch(SQLException e){
			e.printStackTrace();
		}
			
		return "Access Denied";
		
		
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
