package com.guilherme.estoque.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Document
public class ProdutoOrdemEntrega {

	@Id
	private String id;

	private int quantidade;

	private double desconto;
	
	private Produto produto;

	
	public double getTotal() {
		if(Objects.nonNull(produto) && Objects.nonNull(quantidade)) {
			return produto.getPreco()*quantidade;
		}
		else return 0;
	}
	
}
