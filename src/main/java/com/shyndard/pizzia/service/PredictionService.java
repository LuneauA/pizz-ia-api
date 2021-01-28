package com.shyndard.pizzia.service;

import javax.ws.rs.POST;

import com.shyndard.pizzia.entity.dto.CallPredictionDto;
import com.shyndard.pizzia.entity.dto.PredictionDto;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface PredictionService {

    @POST
    PredictionDto getPrediction(CallPredictionDto callPredictionDto);
}