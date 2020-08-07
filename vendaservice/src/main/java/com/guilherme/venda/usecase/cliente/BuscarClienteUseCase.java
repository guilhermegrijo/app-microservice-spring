package com.guilherme.venda.usecase.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guilherme.venda.repository.ClienteRepository;

import rx.Single;

@Service
public class BuscarClienteUseCase {

	private ClienteRepository repository;

	@Autowired
	public BuscarClienteUseCase(ClienteRepository repository) {
		this.repository = repository;
	}

	public Single<?> execute(Optional<String> nome) {
		if (!nome.isPresent()) {
			return Single.just(repository.findAll());
		}
		return Single.just(repository.findByMerchantNameIgnoreCaseContaining(nome.get()));

	}

}
