package com.guilherme.venda.service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.guilherme.venda.model.Produto;
import com.guilherme.venda.usecase.produto.AtualizarProdutoUseCase;
import com.guilherme.venda.usecase.produto.BuscarProdutoUseCase;
import com.guilherme.venda.usecase.produto.SalvarProdutoUseCase;

@Service
public class ProdutoService {

	private BuscarProdutoUseCase buscar;

	private SalvarProdutoUseCase salvar;

	private AtualizarProdutoUseCase atualizar;

	private KafkaTemplate<String, Produto> kafkaTemplate;

	private ResponseEntity<?> response;

	@Autowired
	public ProdutoService(SalvarProdutoUseCase salvarUseCase, BuscarProdutoUseCase buscarUseCase,
			AtualizarProdutoUseCase atualizar) {

		this.salvar = salvarUseCase;
		this.buscar = buscarUseCase;
		this.atualizar = atualizar;
	}

	public ResponseEntity<?> getProduct(Optional<String> nome) {
		buscar.execute(nome).subscribe(this::okStatus, error -> errorStatus(error.getMessage()));
		return response;
	}

	public ResponseEntity<?> updateProduct(Produto produto, String id) {
		atualizar.execute(produto, id).subscribe(this::okStatus, error -> errorStatus(error.getMessage()));
		return response;
	}

	public ResponseEntity<?> newProduct(Produto produto) {
		salvar.execute(produto).subscribe(success -> {
			createdStatus(success);
		}, error -> conflictStatus(error.getMessage()));
		return response;
	}

	private <T> void createdStatus(T entity) {
		response = ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}

	private <T> void okStatus(T entity) {
		response = ResponseEntity.status(HttpStatus.OK).body(entity);
	}

	private <T> void conflictStatus(T entity) {
		response = ResponseEntity.status(HttpStatus.CONFLICT).body(entity);
	}

	private <T> void errorStatus(T entity) {
		response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
	}

}
