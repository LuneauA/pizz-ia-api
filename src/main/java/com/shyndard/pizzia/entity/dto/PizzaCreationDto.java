package com.shyndard.pizzia.entity.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaCreationDto {
    
    @NotBlank
    private String imageBase64;
}
