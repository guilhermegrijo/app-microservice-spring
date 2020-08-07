package com.guilherme.estoque.model;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class OrdemCompraDeserializer extends JsonDeserializer<OrdemEntrega>{
	
	public OrdemCompraDeserializer() {
		super(OrdemEntrega.class);
	}

}
