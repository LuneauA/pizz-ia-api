package com.shyndard.pizzia.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.shyndard.pizzia.entity.Pizza;
import com.shyndard.pizzia.service.PizzaService;

@Path("/pizzas")
public class PizzaController {
    
    @Inject
    PizzaService pizzaService;

    @GET
    public List<Pizza> getAll() {
        return pizzaService.getAll();
    }

    @GET
    @Path("/last")
    public Pizza getLast() {
        return pizzaService.getLast();
    }

    @POST
    public Pizza create(@NotBlank String imgInBase64) {
        return pizzaService.create(imgInBase64);
    }
    
}
