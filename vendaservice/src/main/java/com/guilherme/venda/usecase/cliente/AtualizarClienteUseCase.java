package com.guilherme.venda.usecase.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.venda.model.Cliente;
import com.guilherme.venda.repository.ClienteRepository;

import rx.Single;

@Service
public class AtualizarClienteUseCase {

	private ClienteRepository repository;

	@Autowired
	public AtualizarClienteUseCase(ClienteRepository repository) {
		this.repository = repository;
	}

	public Single<?> execute(Cliente Cliente, String id) {
		if (repository.findById(id) == null) {
			return Single.error(new Exception("Cliente inexistente")); // TODO: mudar a forma de resposta
		}
		Cliente.setId(id);
		return Single.just(repository.save(Cliente));
	}
}
