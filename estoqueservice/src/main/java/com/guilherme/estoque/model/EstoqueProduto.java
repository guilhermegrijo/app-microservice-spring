package com.guilherme.estoque.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
@Document
public class EstoqueProduto implements Serializable {
		
	private Produto produto;	
	
	private int quantidade;
		
	public EstoqueProduto( int quantidade) {
		this.quantidade = quantidade;
	}
	
}
