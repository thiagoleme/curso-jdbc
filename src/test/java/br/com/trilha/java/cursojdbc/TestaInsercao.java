package br.com.trilha.java.cursojdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestaInsercao {

	@Test
	public void InsereNoBanco() {
		try {
			Connection connection = Database.getConnection();

			String nome = "Notebook' ";
			String descricao = "Notebook i5";
								
			String sql = "insert into Produto (nome, descricao) values(?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, nome);
			statement.setString(2, descricao);

			statement.execute();

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
