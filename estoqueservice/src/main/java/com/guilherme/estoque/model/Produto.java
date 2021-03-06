package com.guilherme.estoque.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @Document
public class Produto {
	
	@Id
	private String id;
	
	private String nome;
	
	private float preco;
	
	

	public Produto(String nome, float preco) {
		this.nome = nome;
		this.preco = preco;		
	}	
	
}
