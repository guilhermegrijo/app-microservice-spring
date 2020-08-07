package com.guilherme.estoque;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.guilherme.estoque.model.Estoque;
import com.guilherme.estoque.model.EstoqueProduto;
import com.guilherme.estoque.model.OrdemEntrega;
import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.model.ProdutoOrdemEntrega;
import com.guilherme.estoque.repository.EstoqueRepository;
import com.guilherme.estoque.repository.OrdemEntregaRepository;
import com.guilherme.estoque.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@SpringBootApplication
public class EstoqueApplication{

	/*
	 * private final EstoqueRepository estoqueRepository; private final
	 * ProdutoRepository produtoRepository;private final OrdemEntregaRepository
	 * ordemEntregaRepository;
	 */

	public static void main(String... args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}

/*	@Override
	public void run(String... args) {
		Estoque estoqueA = new Estoque("Estoque A");
		Estoque estoqueB = new Estoque("Estoque B");

		
		  estoqueRepository.saveAll(Arrays.asList(estoqueA, estoqueB));
		  
		  Produto produto = new Produto("Produto A", 20); 
		  Produto produto1 = new Produto("Produto B",15); 
		  produtoRepository.save(produto);
		  produtoRepository.save(produto1);
		  
		  OrdemEntrega ordemEntrega = new OrdemEntrega();
		  
		  ProdutoOrdemEntrega produtoOrdemEntrega = new ProdutoOrdemEntrega();
		  produtoOrdemEntrega.setDesconto(0); produtoOrdemEntrega.setProduto(produto);
		  produtoOrdemEntrega.setQuantidade(20);
		  produtoOrdemEntrega.setOrdemEntrega(ordemEntrega); 
		  ProdutoOrdemEntrega produtoOrdemEntrega1 = new ProdutoOrdemEntrega();
		  produtoOrdemEntrega1.setDesconto(0);
		  produtoOrdemEntrega1.setProduto(produto1);
		  produtoOrdemEntrega1.setQuantidade(10);
		  produtoOrdemEntrega1.setOrdemEntrega(ordemEntrega);
		  ordemEntrega.setProdutoOrdemEntrega(produtoOrdemEntrega, produtoOrdemEntrega1); 
		  ordemEntregaRepository.save(ordemEntrega);
	}
	*/
}
