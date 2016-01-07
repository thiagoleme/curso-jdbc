package br.com.trilha.java.cursojdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestaInsercao {

	@Test
	public void InsereNoBanco() {
		try {
			Connection connection = Database.getConnection();

			Statement statement = connection.createStatement();

			statement.execute("insert into produto (nome, descricao) values ('Notebook', 'Notebook i5')",
					Statement.RETURN_GENERATED_KEYS);

			ResultSet generatedKeys = statement.getGeneratedKeys();

			while (generatedKeys.next()) {
				System.out.println("ID gerado: " + generatedKeys.getString("id"));
			}

			statement.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
