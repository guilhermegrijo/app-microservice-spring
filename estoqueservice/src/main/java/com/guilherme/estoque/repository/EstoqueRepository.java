package com.guilherme.estoque.repository;


import org.springframework.data.mongodb.repository.MongoRepository;


import com.guilherme.estoque.model.Estoque;

public interface EstoqueRepository extends MongoRepository<Estoque, String>{

	Estoque findByNome(String nome);
}
