package com.shyndard.pizzia.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallPredictionDto {
    
    private String img;

    public CallPredictionDto() {
    }

    public CallPredictionDto(String img) {
        this.img = img;
    }
}
