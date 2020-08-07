package com.guilherme.venda.usecase.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.venda.model.Cliente;
import com.guilherme.venda.repository.ClienteRepository;

import rx.Single;

@Service
public class SalvarClienteUseCase {

	private ClienteRepository repository;

	@Autowired
	public SalvarClienteUseCase(ClienteRepository repository) {
		this.repository = repository;
	}

	public Single<?> execute(Cliente Cliente) {
		if (repository.findById(Cliente.getId()) != null) {
			return Single.error(new Exception("Cliente existente")); // TODO: mudar a forma de resposta
		}
		return Single.just(repository.save(Cliente));
	}
}
