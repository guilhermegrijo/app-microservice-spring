package com.guilherme.venda.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.venda.model.Produto;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NovoProdutoKafkaListener {

	private ProdutoService service;

	@Autowired
	public NovoProdutoKafkaListener(ProdutoService service) {
		this.service = service;
	}

	@KafkaListener(topics = "newProduct", groupId = "produto")
	public void newProductConsumer(final ConsumerRecord consumerRecord) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		log.info("key: " + consumerRecord.key());
		log.info("Headers: " + consumerRecord.headers());
		log.info("Partion: " + consumerRecord.partition());
		log.info("Order: " + mapper.writeValueAsString(consumerRecord.value()));
		Produto produto = (Produto) consumerRecord.value();
		service.newProduct(produto);
	}

	@KafkaListener(topics = "updateProduct", groupId = "produto")
	public void updateProductConsumer(final ConsumerRecord consumerRecord) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		log.info("key: " + consumerRecord.key());
		log.info("Headers: " + consumerRecord.headers());
		log.info("Partion: " + consumerRecord.partition());
		log.info("Order: " + mapper.writeValueAsString(consumerRecord.value()));
		Produto produto = (Produto) consumerRecord.value();
		service.updateProduct(produto, produto.getId());
	}

}
