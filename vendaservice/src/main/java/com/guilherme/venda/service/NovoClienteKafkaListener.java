package com.guilherme.venda.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.venda.model.Cliente;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NovoClienteKafkaListener {

	private ClienteService service;

	@Autowired
	public NovoClienteKafkaListener(ClienteService service) {
		super();
		this.service = service;
	}

	@KafkaListener(topics = "newMerchant", groupId = "merchant")
	private void newMerchantConsumer(final ConsumerRecord consumerRecord) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		log.info("key: " + consumerRecord.key());
		log.info("Headers: " + consumerRecord.headers());
		log.info("Partion: " + consumerRecord.partition());
		log.info("Order: " + mapper.writeValueAsString(consumerRecord.value()));
		Cliente cliente = (Cliente) consumerRecord.value();
		service.newMerchant(cliente);
	}

	@KafkaListener(topics = "updateMerchant", groupId = "merchant")
	private void updateMerchantConsumer(final ConsumerRecord consumerRecord) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		log.info("key: " + consumerRecord.key());
		log.info("Headers: " + consumerRecord.headers());
		log.info("Partion: " + consumerRecord.partition());
		log.info("Order: " + mapper.writeValueAsString(consumerRecord.value()));
		Cliente cliente = (Cliente) consumerRecord.value();
		service.updateMerchant(cliente, cliente.getId());
	}

}
