package com.guilherme.estoque.usecase;



import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.Estoque;
import com.guilherme.estoque.repository.EstoqueRepository;

import rx.Single;

@Service
public class AtualizarEstoqueUseCase {

	private EstoqueRepository repository;
		
	@Autowired
	public AtualizarEstoqueUseCase(EstoqueRepository repository) {
		this.repository = repository;
	}
	
	public Single<?> execute(Estoque estoque, String id) {
		if(repository.findById(id) == null) {
			return Single.error(new Exception("Estoque inexistente")); //TODO: mudar a forma de resposta
		}		
		estoque.setId(id);	
		return Single.just(repository.save(estoque));
	}	
}
