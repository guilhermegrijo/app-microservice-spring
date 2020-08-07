package com.guilherme.venda.model;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class ProdutoDeserializer extends JsonDeserializer<Produto> {

	public ProdutoDeserializer() {
		super(Produto.class);
	}

}
