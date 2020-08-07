package com.guilherme.estoque.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.Estoque;
import com.guilherme.estoque.usecase.AtualizarEstoqueUseCase;
import com.guilherme.estoque.usecase.BuscarEstoqueUseCase;
import com.guilherme.estoque.usecase.SalvarEstoqueUseCase;

@Service
public class EstoqueService {

	private SalvarEstoqueUseCase salvar;
	
	private BuscarEstoqueUseCase buscar;
	
	private AtualizarEstoqueUseCase atualizar;
	
	private ResponseEntity<?> response;
	
	
	@Autowired
	public EstoqueService(SalvarEstoqueUseCase salvar, BuscarEstoqueUseCase buscar, AtualizarEstoqueUseCase atualizar) {
		this.salvar = salvar;
		this.buscar = buscar;
		this.atualizar = atualizar;
	}
	
	public ResponseEntity<?> newWarehouse(Estoque estoque){
		salvar.execute(estoque).subscribe(success -> createdStatus(success),
				error -> errorStatus(error.getMessage()));
		return response;
	}
	
	public ResponseEntity<?> getWarehouse(Optional<String> nome){
		buscar.execute(nome).subscribe(success -> okStatus(success),
				error -> errorStatus(error.getMessage()));
		return response;
	}
	
	public ResponseEntity<?> updateWarehouse(Estoque estoque, String id){
		atualizar.execute(estoque, id).subscribe(success -> okStatus(success),
				error -> errorStatus(error.getMessage()));
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
