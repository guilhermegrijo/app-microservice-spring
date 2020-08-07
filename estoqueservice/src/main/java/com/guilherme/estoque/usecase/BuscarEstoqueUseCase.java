package com.guilherme.estoque.usecase;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.repository.EstoqueRepository;

import rx.Single;

@Service
public class BuscarEstoqueUseCase {

	private EstoqueRepository repository;
		
	@Autowired
	public BuscarEstoqueUseCase(EstoqueRepository repository) {
		this.repository = repository;
	}
	
	public Single<?> execute(Optional<String> nome){
		if(!nome.isPresent()) {
			return Single.just(repository.findAll());
		}
		return Single.just(repository.findByNome(nome.get()));
	}
	
}
