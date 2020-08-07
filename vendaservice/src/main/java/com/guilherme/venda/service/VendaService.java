
package com.guilherme.venda.service;

import java.io.IOException;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mercadopago.exceptions.MPException;
import com.guilherme.venda.model.Venda;
import com.guilherme.venda.model.VendaMP;
import com.guilherme.venda.usecase.AtualizarVendaUseCase;
import com.guilherme.venda.usecase.BuscarVendaUseCase;
import com.guilherme.venda.usecase.EnviarEmailUseCase;
import com.guilherme.venda.usecase.NovaVendaMercadoPagoUseCase;
import com.guilherme.venda.usecase.SalvarVendaUseCase;

@Service
public class VendaService {
		
	
	private BuscarVendaUseCase buscar;

	private SalvarVendaUseCase salvar;
	
	private AtualizarVendaUseCase atualizar;

	private KafkaTemplate<String, Venda> kafkaTemplate;

	private ResponseEntity<?> response;

	@Autowired
	public VendaService(
			SalvarVendaUseCase salvarUseCase,
			BuscarVendaUseCase buscarUseCase,
			AtualizarVendaUseCase atualizar,
			KafkaTemplate<String, Venda> kafkaTemplate) {
		
		this.salvar = salvarUseCase;
		this.kafkaTemplate = kafkaTemplate;
		this.buscar = buscarUseCase;
		this.atualizar = atualizar;
		this.vendaMP = vendaMP;
		this.enviarEmail = enviarEmail;
	}

	
	public ResponseEntity<?> getSales(Optional<String> nome, Pageable pageable) {
		buscar.execute(nome, pageable).subscribe(
				this::okStatus,
				error -> errorStatus(error.getMessage()));
		return response;
	}

	
	public ResponseEntity<?> updateSale(Venda taxa, ObjectId id){
		atualizar.execute(taxa, id).subscribe(
				this::okStatus,
				error -> errorStatus(error.getMessage()));
		return response;
	}
	
	
	public ResponseEntity<?> newSale(Venda taxa) {
		salvar.execute(taxa).subscribe(
				success -> {
					createdStatus(success); 
					fireTaxCreatedEvent((Venda) success);
				},
				error -> conflictStatus(error.getMessage()));
		return response;
	}

	private void fireTaxCreatedEvent(Venda venda) {
		kafkaTemplate.send("venda", venda.getId(), venda);
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
