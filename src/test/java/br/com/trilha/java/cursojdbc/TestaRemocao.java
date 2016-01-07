package br.com.trilha.java.cursojdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestaRemocao {

	@Test
	public void RemoveDoBanco() {
		try {
			Connection connection = Database.getConnection();

			Statement statement = connection.createStatement();

			statement.execute("delete from Produto where id > 3");

			System.out.println("Qtde de Registros deletados: " + statement.getUpdateCount());

			statement.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
