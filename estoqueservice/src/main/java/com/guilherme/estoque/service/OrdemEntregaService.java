package com.guilherme.estoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.OrdemEntrega;
import com.guilherme.estoque.usecase.AtualizarOrdemEntregaUseCase;
import com.guilherme.estoque.usecase.BuscarOrdemEntregaUseCase;
import com.guilherme.estoque.usecase.SalvarOrdemEntregaUseCase;

@Service
public class OrdemEntregaService {

	private SalvarOrdemEntregaUseCase salvar;

	private BuscarOrdemEntregaUseCase buscar;
	
	 private AtualizarOrdemEntregaUseCase atualizar;

	private ResponseEntity<?> response;

	@Autowired
	public OrdemEntregaService(SalvarOrdemEntregaUseCase salvar, BuscarOrdemEntregaUseCase buscar,  AtualizarOrdemEntregaUseCase atualizar) {
		this.salvar = salvar;
		this.buscar = buscar;
		this.atualizar = atualizar;
	}

	public ResponseEntity<?> newOrderDelivery(OrdemEntrega ordemEntrega) {
		salvar.execute(ordemEntrega).subscribe(success -> createdStatus(success),
				error -> errorStatus(error.getMessage()));
		return response;
	}

	public ResponseEntity<?> findOrderDelivery() {
		buscar.execute().subscribe(success -> okStatus(success), error -> errorStatus(error.getMessage()));
		return response;
	}

	public ResponseEntity<?> updateOrderDelivery(String id, OrdemEntrega ordemEntrega) {
		atualizar.execute(ordemEntrega, id).subscribe(this::okStatus, this::errorStatus);

		return this.response;
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
