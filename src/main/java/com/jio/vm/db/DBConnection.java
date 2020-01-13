package com.jio.vm.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		String name = "root";
		String password = "root";
		String host = "localhost";
		String db = "vending_machine";

		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + host + ":3306/" + db;

		Connection connection = DriverManager.getConnection(url, name, password);
		connection.setAutoCommit(true);
		return connection;
	}
}

