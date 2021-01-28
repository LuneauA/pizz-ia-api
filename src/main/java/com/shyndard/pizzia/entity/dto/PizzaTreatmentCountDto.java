package com.shyndard.pizzia.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaTreatmentCountDto {
    
    private int count;

    public PizzaTreatmentCountDto() {
    }

    public PizzaTreatmentCountDto(int count) {
        this.count = count;
    }
}
