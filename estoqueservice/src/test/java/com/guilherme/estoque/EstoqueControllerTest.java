package com.guilherme.estoque;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.estoque.controller.EstoqueController;
import com.guilherme.estoque.model.Endereco;
import com.guilherme.estoque.model.Estoque;
import com.guilherme.estoque.model.EstoqueProduto;
import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.repository.EstoqueRepository;
import com.guilherme.estoque.service.EstoqueService;
import com.guilherme.estoque.usecase.AtualizarEstoqueUseCase;
import com.guilherme.estoque.usecase.BuscarEstoqueUseCase;
import com.guilherme.estoque.usecase.SalvarEstoqueUseCase;

@RunWith(SpringRunner.class)
@DataMongoTest
public class EstoqueControllerTest {


	private EstoqueController controller;
	
	@Autowired
	private EstoqueRepository repo;
	
	private EstoqueService service;
	
	private SalvarEstoqueUseCase salvar;
	private BuscarEstoqueUseCase buscar;
	private AtualizarEstoqueUseCase atualizar;

	private MockMvc endpoint;

	@Before
	public void setup() {
		salvar = new SalvarEstoqueUseCase(repo);
		buscar = new BuscarEstoqueUseCase(repo);
		atualizar = new AtualizarEstoqueUseCase(repo);
		service = new EstoqueService(salvar,buscar,atualizar);
		controller = new EstoqueController(service);
		endpoint = standaloneSetup(controller).build();
	}

	@Test
	public void CreateWarehouseShouldPass() throws Exception {
		Estoque estoqueA = new Estoque("Estoque G");
		Endereco endereco = new Endereco();
		endereco.setRua("RUA 1");
		estoqueA.setEndereco(endereco);
		Produto produto = new Produto();
		produto.setNome("produto");
		produto.setPreco(1.99f);
		EstoqueProduto EP = new EstoqueProduto();
		EP.setProduto(produto);
		EP.setQuantidade(45);
		Set<EstoqueProduto> set = new HashSet<>();
		set.add(EP);
		estoqueA.setEstoqueProduto(set);
		estoqueA.setId("22");
		String result = new ObjectMapper().writeValueAsString(estoqueA);
		endpoint.perform(post("/estoque").contentType(APPLICATION_JSON).content(result)).andExpect(status().isCreated());	
		
	}
	
	@Test
	public void FindEmptyWarehouseShouldFail() throws Exception {		
		endpoint.perform(get("/estoque").contentType(APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isEmpty())
		 .andDo(MockMvcResultHandlers.print());
	}
	
}
