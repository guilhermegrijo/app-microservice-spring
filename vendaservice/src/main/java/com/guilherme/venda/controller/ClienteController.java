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

@RestController
public class ClienteController {

	private ClienteService service;

	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/cliente", method = RequestMethod.GET, consumes = { "application/json" })
	public ResponseEntity<?> getMerchant(@RequestParam Optional<String> nome) {
		return service.getMerchant(nome);
	}

}
