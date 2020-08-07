package com.guilherme.venda.usecase.produto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.venda.model.Produto;
import com.guilherme.venda.repository.ProdutoRepository;

import rx.Single;

@Service
public class SalvarProdutoUseCase {

	private ProdutoRepository repository;

	@Autowired
	public SalvarProdutoUseCase(ProdutoRepository repository) {
		this.repository = repository;
	}

	public Single<?> execute(Produto produto) {
		if (repository.findByNomeIgnoreCaseContaining(produto.getNome()) != null) {
			return Single.error(new Exception("Produto existente")); // TODO: mudar a forma de resposta
		}
		return Single.just(repository.save(produto));
	}
}
