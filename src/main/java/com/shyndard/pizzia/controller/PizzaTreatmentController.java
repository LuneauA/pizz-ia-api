package com.shyndard.pizzia.controller;

import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shyndard.pizzia.entity.PizzaTreatment;
import com.shyndard.pizzia.entity.dto.PizzaTreatmentCountDto;
import com.shyndard.pizzia.entity.dto.PizzaTreatmentCreationDto;
import com.shyndard.pizzia.service.PizzaTreatmentService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/pizza-treatment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pizza treatment resource", description = "Manage pizza treatment resource")
public class PizzaTreatmentController {

    @Inject
    PizzaTreatmentService pizzaService;

    @GET
    @Operation(summary = "Find all pizza treatments")
    public Set<PizzaTreatment> getAll() {
        return pizzaService.getAll();
    }

    @GET
    @Operation(summary = "Find last pizza treatment")
    @Path("/last")
    public Optional<PizzaTreatment> getLast() {
        return pizzaService.getLast();
    }

    @GET
    @Operation(summary = "Find last 10 pizza treatments")
    @Path("/last-10")
    public Set<PizzaTreatment> getLast10() {
        return pizzaService.getLast10();
    }

    @GET
    @Operation(summary = "Find total pizza treatment")
    @Path("/total")
    public PizzaTreatmentCountDto getTotal() {
        return new PizzaTreatmentCountDto(pizzaService.getTotal());
    }

    @GET
    @Operation(summary = "Find total success pizza treatment")
    @Path("/success")
    public PizzaTreatmentCountDto getSuccess() {
        return new PizzaTreatmentCountDto(pizzaService.getTotalSuccess());
    }

    @GET
    @Operation(summary = "Find total success pizza treatment")
    @Path("/failed")
    public PizzaTreatmentCountDto getFailed() {
        return new PizzaTreatmentCountDto(pizzaService.getTotalFailed());
    }

    @POST
    @Operation(summary = "Create a pizza treatment")
    public PizzaTreatment create(@Valid final PizzaTreatmentCreationDto pizzaCreationDto) {
        return pizzaService.create(pizzaCreationDto.getImageBase64());
    }

    @DELETE
    @Operation(summary = "Delete all treatment")
    @Path("/delete-all")
    public int deleteAll() {
        return pizzaService.deleteAll();
    }
}
