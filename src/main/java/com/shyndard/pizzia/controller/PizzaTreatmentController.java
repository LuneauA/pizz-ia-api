package com.shyndard.pizzia.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.shyndard.pizzia.entity.PizzaTreatment;
import com.shyndard.pizzia.entity.dto.PizzaTreatmentCountDto;
import com.shyndard.pizzia.entity.dto.PizzaTreatmentCreationDto;
import com.shyndard.pizzia.service.PizzaTreatmentService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/pizza-treatment")
@Tag(name = "Pizza treatment resource", description = "Manage pizza treatment resource")
public class PizzaTreatmentController {

    @Inject
    PizzaTreatmentService pizzaService;

    @GET
    @Operation(summary = "Find all pizza treatments")
    public List<PizzaTreatment> getAll() {
        return pizzaService.getAll();
    }

    @GET
    @Operation(summary = "Find last pizza treatment")
    @Path("/last")
    public PizzaTreatment getLast() {
        return pizzaService.getLast();
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
    

}
