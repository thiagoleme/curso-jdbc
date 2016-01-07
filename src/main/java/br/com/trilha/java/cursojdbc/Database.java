package br.com.trilha.java.cursojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	static String connection = "jdbc:hsqldb:hsql://localhost/loja-virtual";
	static String user = "SA";
	static String password = "";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connection, user, password);
	}
}
