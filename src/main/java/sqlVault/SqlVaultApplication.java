package main.java.sqlVault;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class SqlVaultApplication{
	
	public static void main(String[] args) {
		initializeDB();
		SpringApplication.run(SqlVaultApplication.class,args);
	}
	
	public static void initializeDB() {
		SqlVaultDAO.initialSetup();
	}
}