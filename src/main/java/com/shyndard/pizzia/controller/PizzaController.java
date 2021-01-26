package com.shyndard.pizzia.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.shyndard.pizzia.entity.Pizza;
import com.shyndard.pizzia.entity.dto.PizzaCreationDto;
import com.shyndard.pizzia.service.PizzaService;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/pizzas")
@Tag(name = "Pizza Resource", description = "Manage pizza resource")
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
    public Pizza create(@Valid PizzaCreationDto pizzaCreationDto) {
        return pizzaService.create(pizzaCreationDto.getImageBase64());
    }

}
