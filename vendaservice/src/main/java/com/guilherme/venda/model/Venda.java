package com.guilherme.venda.model;

import java.util.Date;
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
public class Venda {

	@Id
	private String id;

	@CreatedDate
	private Date createdTime;

	private Set<ProdutoVenda> listaProdutos = new HashSet<>();

	public void setListaProdutos(ProdutoVenda... produtoOrdemEntrega) {
		this.listaProdutos.addAll(Stream.of(produtoOrdemEntrega).collect(Collectors.toSet()));
	}

	public double getTotal() {
		Double total = listaProdutos.stream().mapToDouble(ProdutoVenda::getTotal).sum();
		return total;
	}

	public int getTotalProducts() {
		return listaProdutos.stream().mapToInt(ProdutoVenda::getQuantidade).sum();
	}

}
