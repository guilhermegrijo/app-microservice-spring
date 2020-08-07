package com.guilherme.venda.usecase.produto;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.venda.model.Produto;
import com.guilherme.venda.repository.ProdutoRepository;

import rx.Single;

@Service
public class AtualizarProdutoUseCase {

	private ProdutoRepository repository;

	@Autowired
	public AtualizarProdutoUseCase(ProdutoRepository repository) {
		this.repository = repository;
	}

	public Single<?> execute(Produto produto, String id) {
		if (repository.findById(id) == null) {
			return Single.error(new Exception("Produto inexistente")); // TODO: mudar a forma de resposta
		}
		produto.setId(id);
		return Single.just(repository.save(produto));
	}
}
