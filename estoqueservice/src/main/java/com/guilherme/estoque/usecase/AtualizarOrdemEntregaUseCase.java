package com.guilherme.estoque.usecase;


import com.guilherme.estoque.model.OrdemEntrega;
import com.guilherme.estoque.repository.OrdemEntregaRepository;
import com.guilherme.estoque.usecase.AtualizarOrdemEntregaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;





@Service
public class AtualizarOrdemEntregaUseCase
{
  private OrdemEntregaRepository repository;
  
  @Autowired
  public AtualizarOrdemEntregaUseCase(OrdemEntregaRepository repository) { this.repository = repository; }

  
  public Single<?> execute(OrdemEntrega ordemEntrega, String id) {
    if (this.repository.findById(id) == null) {
      return Single.error(new Exception("Entrega inexistente"));
    }
    ordemEntrega.setId(id);
    return Single.just((OrdemEntrega)this.repository.save(ordemEntrega));
  }
}