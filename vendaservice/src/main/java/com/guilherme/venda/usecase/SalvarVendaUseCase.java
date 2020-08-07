package com.guilherme.venda.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.venda.model.Venda;
import com.guilherme.venda.repository.VendaRepository;

import rx.Single;

@Service
public class SalvarVendaUseCase {

	private VendaRepository repository;

	@Autowired
	public SalvarVendaUseCase(VendaRepository taxaRepository) {
		this.repository = taxaRepository;
	}

	public Single<?> execute(Venda taxa) {
		return Single.just(repository.save(taxa));
	}
}
