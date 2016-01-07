package br.com.trilha.java.cursojdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	Integer id;
	String nome;
	private final List<Produto> produtos = new ArrayList<>();

	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public void adiciona(Produto p){
		produtos.add(p);
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
