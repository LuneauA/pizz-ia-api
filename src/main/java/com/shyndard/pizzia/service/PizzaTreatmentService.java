package com.shyndard.pizzia.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.shyndard.pizzia.entity.PizzaTreatment;
import com.shyndard.pizzia.entity.dto.CallPredictionDto;
import com.shyndard.pizzia.entity.dto.PredictionDto;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PizzaTreatmentService {

	@Inject
	S3StorageService storageService;

	@Inject
	@RestClient
	PredictionService predictionService;

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

	public int getTotal() {
		return 0;
	}

	public int getTotalSuccess() {
		return 0;
	}

	public int getTotalFailed() {
		return 0;
	}

	public PizzaTreatment create(final String imgInBase64) {
		PizzaTreatment pizza = new PizzaTreatment(UUID.randomUUID());
		// Store image in S3 bucket
		Optional<URL> url = storageService.upload(UUID.randomUUID().toString(), imgInBase64);
		if (url.isPresent()) {
			pizza.setImageUrl(url.get());
		} else {
			throw new WebApplicationException("Cannot upload image to s3", 500);
		}
		// Call prediction API
		PredictionDto prediction = predictionService.getPrediction(new CallPredictionDto(imgInBase64));
		if(prediction == null) {
			throw new WebApplicationException("Cannot get a valid prediction", 500);
		} else {
			pizza.setSuccess(prediction.getResult());
		}
		// TODO: Save prediction's result in database
		return pizza;
	}
}