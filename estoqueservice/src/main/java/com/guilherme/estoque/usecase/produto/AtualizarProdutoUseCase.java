package com.guilherme.estoque.usecase.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.repository.ProdutoRepository;

import rx.Single;

@Service
public class AtualizarProdutoUseCase {

	
	private ProdutoRepository repository;
	
	@Autowired
	public AtualizarProdutoUseCase(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public Single<?> execute(Produto produto, String id) {
		if(repository.findById(id) == null) {
			return Single.error(new Exception("Produto inexistente")); //TODO: mudar a forma de resposta
		}		
		produto.setId(id);
		return Single.just(repository.save(produto));
	}
}
