package com.guilherme.estoque.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.estoque.model.OrdemEntrega;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NewSaleKafkaListener {
	
	private OrdemEntregaService service;
	
	@Autowired
	NewSaleKafkaListener(OrdemEntregaService taxaService){
		this.service = taxaService;
	}
	
	
	@KafkaListener(topics="venda", groupId ="venda")
	public void consumer(final ConsumerRecord consumerRecord) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		 	log.info("key: " + consumerRecord.key());
	        log.info("Headers: " + consumerRecord.headers());
	        log.info("Partion: " + consumerRecord.partition());
	        log.info("Order: " + mapper.writeValueAsString(consumerRecord.value()));
	        OrdemEntrega ordemEntrega = (OrdemEntrega) consumerRecord.value();
	        service.newOrderDelivery(ordemEntrega);	        
	}
}
