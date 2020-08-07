package com.guilherme.venda.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.guilherme.venda.model.Venda;

public interface VendaRepository extends PagingAndSortingRepository<Venda, String> {

	Venda findById(final ObjectId id);

	Page<Venda> findByClienteMerchantName(final String nome, Pageable pageable);
}
