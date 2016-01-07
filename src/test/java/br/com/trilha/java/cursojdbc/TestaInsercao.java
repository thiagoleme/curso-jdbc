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
		try (Connection connection = Database.getConnection()) {
			String sql = "insert into Produto (nome, descricao) values(?, ?)";
			connection.setAutoCommit(false);
			
			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				String nome = "TV LCD";
				String descricao = "TV LCD, 32 polegadas";
				adiciona(nome, descricao, statement);

				nome = "Blueray";
				descricao = "Blueray, Full HDMI";
				adiciona(nome, descricao, statement);
				
				connection.commit();
			}catch (Exception e) {
				e.printStackTrace();
				connection.rollback();
				System.out.println("rollback efetuado");
			}

		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		if (nome.equals("Blueray")) {
			throw new IllegalArgumentException("Problema ocorrido");
		}

		statement.setString(1, nome);
		statement.setString(2, descricao);

		statement.execute();

		ResultSet generatedKeys = statement.getGeneratedKeys();

		while (generatedKeys.next()) {
			System.out.println("ID gerado: " + generatedKeys.getString("id"));
		}
		generatedKeys.close();
	}
}
