package com.guilherme.estoque.usecase;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.Estoque;
import com.guilherme.estoque.repository.EstoqueRepository;

import rx.Single;

@Service
public class SalvarEstoqueUseCase {

	private EstoqueRepository repository;
	
	@Autowired
	public SalvarEstoqueUseCase(EstoqueRepository repository) {
		this.repository = repository;
	}
	
	public Single<?> execute(Estoque estoque){
		if(repository.findByNome(estoque.getNome()) !=null) {
			return Single.error(new Exception("Nome existente")); //TODO: mudar a forma de resposta
		}
		return Single.just(repository.save(estoque));
	}
	
}
