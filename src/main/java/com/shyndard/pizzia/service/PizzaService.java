package com.shyndard.pizzia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;

import com.shyndard.pizzia.entity.Pizza;

@ApplicationScoped
public class PizzaService {

	private List<Pizza> pizzas = new ArrayList<>();

	public List<Pizza> getAll() {
		return pizzas;
	}

	public Pizza getLast() {
		if (pizzas.isEmpty()) {
			throw new WebApplicationException("No data found", 404);
		} else {
			return pizzas.get(pizzas.size() - 1);
		}
	}

	public Pizza create(String imgInBase64) {
		Pizza pizza = new Pizza(UUID.randomUUID());
		return pizza;
	}
}
