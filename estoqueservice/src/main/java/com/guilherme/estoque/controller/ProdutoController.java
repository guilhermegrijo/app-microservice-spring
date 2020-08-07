package com.guilherme.estoque.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.estoque.model.Produto;
import com.guilherme.estoque.service.ProdutoService;

@RestController
public class ProdutoController {


	private ProdutoService service;

	@Autowired
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@ResponseBody
	@RequestMapping(value = "/produto", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<?> newProduct(@RequestBody Produto produto) {
		return service.newProduct(produto);
	}
	
	@ResponseBody
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.PUT, consumes = {"application/json"})
	public ResponseEntity<?> updateProduct(@RequestBody Produto produto, @PathVariable("id") String id){
		return service.updateProduct(produto, id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/produto", method = RequestMethod.GET, consumes = {"application/json"})
	public ResponseEntity<?> getProduct(@RequestParam Optional<String> nome){
		return service.getProducts(nome);
	}
	
	
}