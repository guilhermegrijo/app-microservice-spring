package com.guilherme.venda.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.guilherme.venda.repository.ProdutoRepository;
import com.guilherme.venda.repository.VendaRepository;

import rx.Single;

@Service
public class BuscarVendaUseCase {

	private VendaRepository repository;

	@Autowired
	public BuscarVendaUseCase(VendaRepository repository) {
		this.repository = repository;
	}

	public Single<?> execute(Optional<String> nome, Pageable pageable) {
		if (!nome.isPresent()) {
			return Single.just(repository.findAll(pageable));
		}
		return Single.just(repository.findByClienteMerchantName(nome.get(), pageable));

	}
}
