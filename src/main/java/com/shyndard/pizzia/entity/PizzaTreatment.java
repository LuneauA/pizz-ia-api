package com.shyndard.pizzia.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaTreatment {
    
    private UUID id;
    private String imageUrl;
    private String message;

    public PizzaTreatment() {

    }

    public PizzaTreatment(UUID id) {
        this.id = id;
    }
    
}