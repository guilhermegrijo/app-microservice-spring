package com.guilherme.venda.controller;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.exceptions.MPException;
import com.guilherme.venda.model.Venda;
import com.guilherme.venda.model.VendaMP;
import com.guilherme.venda.service.VendaService;

@RestController
public class VendaController {

	private VendaService service;

	@Autowired
	public VendaController(VendaService service) {
		this.service = service;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/venda", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<?> newSale(@RequestBody Venda Venda) {
		return service.newSale(Venda);
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/venda", method = RequestMethod.GET, consumes = { "application/json" })
	public ResponseEntity<?> getSale(@RequestParam Optional<String> nome, Pageable pageable) {
		return service.getSales(nome, pageable);
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/venda/{id}", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<?> updateSale(@RequestBody Venda Venda, @PathVariable("id") ObjectId id) {
		return service.updateSale(Venda, id);
	}
}
