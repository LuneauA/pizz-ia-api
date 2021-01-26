package com.shyndard.pizzia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;

import com.shyndard.pizzia.entity.PizzaTreatment;

@ApplicationScoped
public class PizzaTreatmentService {

	// TODO: Store in a database
	private final List<PizzaTreatment> pizzas = new ArrayList<>();

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

	public PizzaTreatment create(final String imgInBase64) {
		PizzaTreatment pizza = new PizzaTreatment(UUID.randomUUID());
		// TODO: Call prediction API
		// TODO: Store image in S3 bucket
		// TODO: Save prediction's result in database
		return pizza;
	}
}