package br.com.trilha.java.cursojdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.trilha.java.cursojdbc.model.Categoria;
import br.com.trilha.java.cursojdbc.model.Produto;

public class CategoriaDAO {
	private Connection connection;
	private List<Categoria> categorias;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Categoria> lista() throws SQLException {
		categorias = new ArrayList<>();

		String sql = "select * from Categoria";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();

			transformaResultadoEmCategoria(statement);
		}
		return categorias;
	}

	public List<Categoria> listaComProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;
		
		String sql = "select c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao from Categoria as c join Produto as p on p.categoria_id = c.id order by c.id";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("c_id");
					String nome = rs.getString("c_nome");
					
					if(ultima==null || !ultima.getNome().equals(nome)) {
						Categoria categoria = new Categoria(id, nome);
                        categorias.add(categoria);
                        ultima = categoria;
                    }
					
					int p_id = rs.getInt("p_id");
					String p_nome = rs.getString("p_nome");
					String p_descricao = rs.getString("p_descricao");
					
					Produto produto = new Produto(p_nome, p_descricao);
					produto.setId(p_id);
					
					ultima.adiciona(produto);
				}
			}
		}
		return categorias;
	}

	private void transformaResultadoEmCategoria(PreparedStatement statement) throws SQLException {
		try (ResultSet resultSet = statement.getResultSet()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");

				Categoria c = new Categoria(id, nome);

				categorias.add(c);
			}
		}
	}

}
