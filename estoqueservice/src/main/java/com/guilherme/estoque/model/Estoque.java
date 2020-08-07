package com.guilherme.estoque.model;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
@Document
public class Estoque {

	@Id
	private String id;

	private String nome;

	private Endereco endereco;
	
	private Set<EstoqueProduto> estoqueProduto = new HashSet<>();


	public Estoque(String nome) {
		this.nome = nome;
	}

}
