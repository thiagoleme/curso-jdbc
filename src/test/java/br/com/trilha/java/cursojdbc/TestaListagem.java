package br.com.trilha.java.cursojdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestaListagem {

	@Test
	public void RetornaListagem() {
		try {
			Connection connection = Database.getConnection();

			Statement statement = connection.createStatement();
			boolean result = statement.execute("select * from Produto");

			if (result == true) {
				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nome = resultSet.getString("nome");
					String descricao = resultSet.getString("descricao");
					System.out.println("\n\nID: " + id + "\nNome: " + nome + "\nDescrição: " + descricao);
				}
				resultSet.close();
			}
			statement.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
