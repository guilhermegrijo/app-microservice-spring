package com.guilherme.estoque.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class OrdemEntrega implements Serializable {

	@Id	
	private String id;

	private Set<ProdutoOrdemEntrega> listaProdutos = new HashSet<>();
	
	@CreatedDate
	private Date createdTime;
	
	private StatusEntrega status;
	
	private Date previsaoEntrega;
	
	private HashMap<Date,String> acompanhamento;

	public void setProdutoOrdemEntrega(ProdutoOrdemEntrega... produtoOrdemEntrega) {
		this.listaProdutos.addAll(Stream.of(produtoOrdemEntrega).collect(Collectors.toSet()));
	}

	public double getTotal() {
		Double total = listaProdutos.stream().mapToDouble(ProdutoOrdemEntrega::getTotal).sum();
		return total;
	}

}
