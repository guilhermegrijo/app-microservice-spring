
package com.guilherme.estoque.model;

import java.util.Date;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Endereco {

	@JsonProperty("zip_code")
	private int CEP;

	@JsonProperty("street")
	private String rua;

	@JsonProperty("number")
	private int numero;

	@JsonProperty("complement")
	private String complemento;

	@JsonProperty("neighborhood")
	private String bairro;

	@JsonProperty("city")
	private String cidade;

	@JsonProperty("state")
	private String estado;

	@CreatedDate
	private Date created_date;
	

}
