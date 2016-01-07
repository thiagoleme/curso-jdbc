package br.com.trilha.java.cursojdbc.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import br.com.trilha.java.cursojdbc.ConnectionPool;
import br.com.trilha.java.cursojdbc.dao.CategoriaDAO;
import br.com.trilha.java.cursojdbc.dao.ProdutoDAO;

public class TestaCategoria {

	// @Test
	public void ListaCategoria() {
		List<Categoria> lista = null;

		try (Connection connection = new ConnectionPool().getConnection()) {
			lista = new CategoriaDAO(connection).lista();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		for (Categoria categoria : lista) {
			System.out.println("\n\nID: " + categoria.getId() + "\nNome: " + categoria.getNome());
		}
	}

	// @Test
	public void BuscaProdutoPorCategoria() throws SQLException {
		List<Categoria> categorias = null;

		try (Connection connection = new ConnectionPool().getConnection()) {
			categorias = new CategoriaDAO(connection).lista();

			for (Categoria categoria : categorias) {
				for (Produto produto : new ProdutoDAO(connection).busca(categoria)) {
					System.out.println(categoria.getNome() + " - " + produto.getNome());
				}
			}
		}
	}

	@Test
	public void BuscaProdutoPorCategoriaSemNmais1() throws SQLException {
		try (Connection connection = new ConnectionPool().getConnection()) {
			List<Categoria> categorias = new CategoriaDAO(connection).listaComProdutos();

			for (Categoria categoria : categorias) {
				System.out.println(categoria.getNome());
				for (Produto produto : categoria.getProdutos()) {
					System.out.println("  -->" + produto.getNome());
				}
			}
		}
	}

}
