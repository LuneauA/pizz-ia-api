package com.shyndard.pizzia.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.shyndard.pizzia.dao.PizzaTreatmentDao;
import com.shyndard.pizzia.entity.PizzaTreatment;

import io.netty.util.internal.ThreadLocalRandom;

@ApplicationScoped
public class PizzaTreatmentService {

	@Inject
	S3StorageService storageService;

	@Inject
	PizzaTreatmentDao pizzaTreatmentDao;

	List<String> responses = new ArrayList<>(
			Arrays.asList("Il manque de la sauce", "Pas assez de condiments", "Taille incorrecte"));

	public Set<PizzaTreatment> getAll() {
		return pizzaTreatmentDao.findAll();
	}

	public Optional<PizzaTreatment> getLast() {
		return pizzaTreatmentDao.findLast();
	}

	public int getTotal() {
		return pizzaTreatmentDao.countTotal();
	}

	public int getTotalSuccess() {
		return pizzaTreatmentDao.countTotal(1);
	}

	public int getTotalFailed() {
		return pizzaTreatmentDao.countTotal(0);
	}

	public PizzaTreatment create(final String imgInBase64) {
		PizzaTreatment pizza = new PizzaTreatment();
		// Store image in S3 bucket
		Optional<URL> url = storageService.upload(UUID.randomUUID().toString(), imgInBase64);
		if (url.isPresent()) {
			pizza.setImageUrl(url.get());
		} else {
			throw new WebApplicationException("Cannot upload image to s3", 500);
		}
		// Fake call to IA
		pizza.setSuccess(ThreadLocalRandom.current().nextInt(0, 2));
		if (pizza.getSuccess() == 0) {
			pizza.setMessage(responses.get(ThreadLocalRandom.current().nextInt(0, responses.size())));
		} else {
			pizza.setMessage("OK =)");
		}
		pizzaTreatmentDao.create(pizza.getSuccess(), pizza.getImageUrl(), pizza.getMessage());
		return pizza;
	}

	public int deleteAll() {
		return pizzaTreatmentDao.deleteAll();
	}

	public Set<PizzaTreatment> getLast10() {
		return pizzaTreatmentDao.findLast10();
	}
}