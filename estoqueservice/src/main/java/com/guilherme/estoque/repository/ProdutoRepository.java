package com.guilherme.estoque.repository;


import org.springframework.data.mongodb.repository.MongoRepository;


import com.guilherme.estoque.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
	
	Produto findByNome(String nome);

}
