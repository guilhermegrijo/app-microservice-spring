package com.guilherme.estoque.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.repository.OrdemEntregaRepository;

import rx.Single;

@Service
public class BuscarOrdemEntregaUseCase {

	private OrdemEntregaRepository repository;
	
	@Autowired
	public BuscarOrdemEntregaUseCase(OrdemEntregaRepository repository) {
		this.repository = repository;
	}
	
	public Single<?> execute(Optional<Long> id) {
		if(!nome.isPresent()) {
			return Single.just(repository.findAll());
		}
		return Single.just(repository.findById(nome.get()));
	}
}
