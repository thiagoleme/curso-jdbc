package br.com.trilha.java.cursojdbc.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.trilha.java.cursojdbc.ConnectionPool;
import br.com.trilha.java.cursojdbc.dao.ProdutoDAO;

public class TestaProduto {
	private Produto produto;

	@Before
	public void criaProduto() {
		produto = new Produto("Mesa Azul", "Mesa com 4 pés");
	}

	@Test
	public void InsereProdutoNoBanco() {
		try (Connection connection = new ConnectionPool().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(connection);
			dao.salva(produto);
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	@Test
	public void ListaProdutoDoBanco() {
		List<Produto> lista = null;
		
		try (Connection connection = new ConnectionPool().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(connection);
			lista = dao.lista();
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		for (Produto produto : lista) {
			System.out.println(	"\n\nID: " + produto.getId() 
								+ "\nNome: " + produto.getNome() 
								+ "\nDescrição: " + produto.getDescricao());
		}
	}

}
