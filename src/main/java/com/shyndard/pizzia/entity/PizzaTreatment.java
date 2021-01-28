package com.shyndard.pizzia.entity;

import java.net.URL;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaTreatment {
    
    private UUID id;
    private URL imageUrl;
    private int success;
    private String message;

    public PizzaTreatment() {

    }

    public PizzaTreatment(UUID id) {
        this.id = id;
    }
    
}