package com.shyndard.pizzia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;

import com.shyndard.pizzia.entity.PizzaTreatment;

@ApplicationScoped
public class PizzaTreatmentService {

	private List<PizzaTreatment> pizzas = new ArrayList<>();

	public List<PizzaTreatment> getAll() {
		return pizzas;
	}

	public PizzaTreatment getLast() {
		if (pizzas.isEmpty()) {
			throw new WebApplicationException("No data found", 404);
		} else {
			return pizzas.get(pizzas.size() - 1);
		}
	}

	public PizzaTreatment create(String imgInBase64) {
		PizzaTreatment pizza = new PizzaTreatment(UUID.randomUUID());
		return pizza;
	}
}