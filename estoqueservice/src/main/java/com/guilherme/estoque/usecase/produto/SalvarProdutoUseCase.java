package com.guilherme.estoque.usecase.produto;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.repository.ProdutoRepository;

import rx.Single;

@Service
public class SalvarProdutoUseCase {
	
	private ProdutoRepository repository;
	
	
	@Autowired
	public SalvarProdutoUseCase(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public Single<?> execute(Produto produto){
		
		if(repository.findByNome(produto.getNome()) !=null) {
			return Single.error(new Exception("Produto existente")); //TODO: mudar a forma de resposta
		}
		return Single.just(repository.save(produto));
	}
}
