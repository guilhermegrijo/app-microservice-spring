package com.guilherme.estoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.usecase.produto.AtualizarProdutoUseCase;
import com.guilherme.estoque.usecase.produto.BuscarProdutoUseCase;
import com.guilherme.estoque.usecase.produto.SalvarProdutoUseCase;

@Service
public class ProdutoService {

	
	
	private BuscarProdutoUseCase buscar;

	private SalvarProdutoUseCase salvar;
	
	private AtualizarProdutoUseCase atualizar;

	private KafkaTemplate<String, Produto> kafkaTemplate;

	private ResponseEntity<?> response;

	@Autowired
	public ProdutoService(BuscarProdutoUseCase buscar, SalvarProdutoUseCase salvar, 
			AtualizarProdutoUseCase atualizar,KafkaTemplate<String, Produto> kafkaTemplate) {
		this.salvar = salvar;
		this.kafkaTemplate = kafkaTemplate;
		this.buscar = buscar;
		this.atualizar = atualizar;
	}

	//||||||||||||||||||||||BUSCA PRODUTO ||||||||||||||||||||||||\\
	public ResponseEntity<?> getProducts(Optional<String> nome) {
		buscar.execute(nome).subscribe(
				this::okStatus,
				error -> errorStatus(error.getMessage()));
		return response;
	}

	//||||||||||||||||||||||ATUALIZA PRODUTO ||||||||||||||||||||||||\\
	public ResponseEntity<?> updateProduct(Produto produto, String id){
		atualizar.execute(produto, id).subscribe(
				success -> {
					okStatus(success);
					fireUpdateProductEvent((Produto) success);
					},
				error -> errorStatus(error.getMessage()));
		return response;
	}
	
	//||||||||||||||||||NOVO PRODUTO |||||||||||||||||||\\
	public ResponseEntity<?> newProduct(Produto produto) {
		salvar.execute(produto).subscribe(
				success -> {
					createdStatus(success); 
					fireNewProductEvent((Produto) success);
				},
				error -> conflictStatus(error.getMessage()));
		return response;
	}

	private void fireNewProductEvent(Produto produto) {
		kafkaTemplate.send("newProduct", produto.getId(), produto);
	}
	
	private void fireUpdateProductEvent(Produto produto) {
		kafkaTemplate.send("updateProduct", produto.getId(), produto);
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
