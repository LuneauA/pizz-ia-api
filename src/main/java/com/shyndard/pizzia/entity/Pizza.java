package com.shyndard.pizzia.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pizza {
    
    private UUID id;
    private String imageUrl;

    public Pizza() {

    }

    public Pizza(UUID id) {
        this.id = id;
    }
    
}