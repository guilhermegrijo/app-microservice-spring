package com.guilherme.venda.usecase;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.venda.model.Venda;
import com.guilherme.venda.repository.VendaRepository;

import rx.Single;

@Service
public class AtualizarVendaUseCase {

	private VendaRepository repository;

	@Autowired
	public AtualizarVendaUseCase(VendaRepository repository) {
		this.repository = repository;
	}

	public Single<?> execute(Venda venda, ObjectId id) {
		if (repository.findById(id) == null) {
			return Single.error(new Exception("Venda inexistente")); // TODO: mudar a forma de resposta
		}
		return Single.just(repository.save(venda));
	}
}
