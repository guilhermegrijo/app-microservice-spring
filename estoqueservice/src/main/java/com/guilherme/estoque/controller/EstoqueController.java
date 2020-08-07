package com.guilherme.estoque.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.estoque.model.Estoque;
import com.guilherme.estoque.model.OrdemEntrega;
import com.guilherme.estoque.service.EstoqueService;
import com.guilherme.estoque.service.OrdemEntregaService;

@RestController
public class EstoqueController
{
  private EstoqueService service;
  private OrdemEntregaService entregaService;
  
  @Autowired
  public EstoqueController(EstoqueService service, OrdemEntregaService entregaService) {
    this.service = service;
    this.entregaService = entregaService;
  }

  
  @CrossOrigin
  @ResponseBody
  @RequestMapping(value = {"/estoque"}, method = {RequestMethod.POST}, consumes = {"application/json"})
  public ResponseEntity<?> newWarehouse(@RequestBody Estoque estoque) { return this.service.newWarehouse(estoque); }


  
  @CrossOrigin
  @ResponseBody
  @RequestMapping(value = {"/estoque"}, method = {RequestMethod.GET}, consumes = {"application/json"})
  public ResponseEntity<?> getWarehouse(@RequestParam Optional<String> nome) { return this.service.getWarehouse(nome); }


  
  @CrossOrigin
  @ResponseBody
  @RequestMapping(value = {"/estoque/{id}"}, method = {RequestMethod.PUT}, consumes = {"application/json"})
  public ResponseEntity<?> updateWarehouse(@RequestBody Estoque estoque, @PathVariable("id") String id) { return this.service.updateWarehouse(estoque, id); }


  
  @CrossOrigin
  @ResponseBody
  @RequestMapping(value = {"/ordemEntrega"}, method = {RequestMethod.GET}, consumes = {"application/json"})
  public ResponseEntity<?> getDeliveryOrder(@RequestParam Optional<String> nome) { return this.entregaService.findOrderDelivery(nome); }


  
  @CrossOrigin
  @ResponseBody
  @RequestMapping(value = {"/ordemEntrega/{id}"}, method = {RequestMethod.PUT}, consumes = {"application/json"})
  public ResponseEntity<?> getDeliveryOrder(@PathVariable("id") String id, @RequestBody OrdemEntrega ordemEntrega) { return this.entregaService.updateOrderDelivery(id, ordemEntrega); }
}