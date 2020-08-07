package com.guilherme.estoque.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.OrdemEntrega;
import com.guilherme.estoque.repository.OrdemEntregaRepository;

import rx.Single;

@Service
public class SalvarOrdemEntregaUseCase {

	
	private OrdemEntregaRepository repository;
	
	@Autowired
	public SalvarOrdemEntregaUseCase(OrdemEntregaRepository repository) {
		this.repository = repository;
	}
	public Single<?> execute(OrdemEntrega ordemEntrega){
		if(ordemEntrega.getListaProdutos() == null) {
			return Single.error(new Exception("sem produto"));
		}
		return Single.just(repository.save(ordemEntrega));
	}
	
}
