package com.guilherme.venda.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.guilherme.venda.model.Cliente;
import com.guilherme.venda.usecase.cliente.AtualizarClienteUseCase;
import com.guilherme.venda.usecase.cliente.BuscarClienteUseCase;
import com.guilherme.venda.usecase.cliente.SalvarClienteUseCase;

@Service
public class ClienteService {

	private BuscarClienteUseCase buscar;

	private SalvarClienteUseCase salvar;

	private AtualizarClienteUseCase atualizar;

	private KafkaTemplate<String, Cliente> kafkaTemplate;

	private ResponseEntity<?> response;

	@Autowired
	public ClienteService(SalvarClienteUseCase salvarUseCase, BuscarClienteUseCase buscarUseCase,
			AtualizarClienteUseCase atualizar) {

		this.salvar = salvarUseCase;
		this.buscar = buscarUseCase;
		this.atualizar = atualizar;
	}

	public ResponseEntity<?> getMerchant(Optional<String> nome) {
		buscar.execute(nome).subscribe(this::okStatus, error -> errorStatus(error.getMessage()));
		return response;
	}

	public ResponseEntity<?> updateMerchant(Cliente Cliente, String id) {
		atualizar.execute(Cliente, id).subscribe(this::okStatus, error -> errorStatus(error.getMessage()));
		return response;
	}

	public ResponseEntity<?> newMerchant(Cliente Cliente) {
		salvar.execute(Cliente).subscribe(this::createdStatus, error -> conflictStatus(error.getMessage()));
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
