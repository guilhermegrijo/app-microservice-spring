package com.guilherme.venda.usecase.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.venda.repository.ProdutoRepository;

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
		return Single.just(repository.findByNomeIgnoreCaseContaining(nome.get()));

	}

}
