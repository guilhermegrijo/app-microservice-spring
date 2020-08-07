package com.guilherme.venda.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.guilherme.venda.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

	List<Produto> findByNomeIgnoreCaseContaining(String nome);

}
