package com.guilherme.estoque.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.guilherme.estoque.model.OrdemEntrega;

public interface OrdemEntregaRepository extends MongoRepository<OrdemEntrega, Long>{
}
