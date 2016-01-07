package br.com.trilha.java.cursojdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.trilha.java.cursojdbc.model.Produto;

public class ProdutoDAO {
	private Connection connection;
	private List<Produto> produtos;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salva(Produto p) throws SQLException {
		connection.setAutoCommit(false);
		String sql = "insert into Produto (nome, descricao) values(?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, p.getNome());
			statement.setString(2, p.getDescricao());
			statement.execute();

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					p.setId(generatedKeys.getInt("id"));
				}
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			System.out.println("rollback efetuado");
		}
	}

	public List<Produto> lista() {
		produtos = new ArrayList<>();
		
		String sql = "select * from Produto";
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();

			try(ResultSet resultSet = statement.getResultSet()){
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nome = resultSet.getString("nome");
					String descricao = resultSet.getString("descricao");
					
					Produto p = new Produto(nome, descricao);
					p.setId(id);
					
					produtos.add(p);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return produtos;
	}

}
