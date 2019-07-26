package com.carros.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexResource {

	@GetMapping
	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return"Bem vindo a API de Carros";
	}
	
}
