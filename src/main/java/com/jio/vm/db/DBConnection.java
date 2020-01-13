package com.jio.vm.db;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		final Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("E:\\eclipse-workspace\\JioVendingMachine\\src\\main\\resources\\application.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String driver =  properties.getProperty("spring.datasource.driver-class-name");

		String username = properties.getProperty("spring.datasource.username");
		String password = properties.getProperty("spring.datasource.password");

		Class.forName(driver);
		String url = properties.getProperty("spring.datasource.url");

		Connection connection = DriverManager.getConnection(url, username, password);
		connection.setAutoCommit(true);
		return connection;
	}
}

