package com.guilherme.estoque.usecase.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.repository.ProdutoRepository;
import rx.Single;

@Service
public class BuscarProdutoUseCase {

	private ProdutoRepository repository;

	@Autowired
	public BuscarProdutoUseCase(ProdutoRepository repository) {
		this.repository = repository;
	}

	public Single<?> execute(Optional<String> nome) {
		if (!nome.isPresent()) {
			return Single.just(repository.findAll());
		}
		return Single.just(repository.findByNome(nome.get()));

	}

}
