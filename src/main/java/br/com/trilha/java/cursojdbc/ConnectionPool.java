package br.com.trilha.java.cursojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class ConnectionPool {
	static String connection = "jdbc:hsqldb:hsql://localhost/loja-virtual";
	static String user = "SA";
	static String password = "";
	private DataSource dataSource;
	
	public ConnectionPool() {
		JDBCPool pool = new JDBCPool();
		pool.setUrl(connection);
		pool.setUser(user);
		pool.setPassword(password);
		this.dataSource = pool;
	}

	public Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(connection, user, password);
		return dataSource.getConnection();
	}
}
