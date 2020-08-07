package com.guilherme.venda.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.venda.model.Venda;
import com.guilherme.venda.service.ClienteService;
import com.guilherme.venda.service.ProdutoService;

@RestController
public class ProdutoController {

	private ProdutoService service;

	@Autowired
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/produto", method = RequestMethod.GET, consumes = { "application/json" })
	public ResponseEntity<?> getProduct(@RequestParam Optional<String> nome) {
		return service.getProduct(nome);
	}
}
