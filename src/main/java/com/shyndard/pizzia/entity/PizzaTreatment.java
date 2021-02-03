package com.shyndard.pizzia.entity;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaTreatment {

    private UUID id;
    private String imageUrl;
    private int success;
    private String message;
    private Timestamp createdAt;

    public PizzaTreatment() {
        createdAt = new Timestamp(new Date().getTime());
    }

    public void setImageUrl(String url) {
        imageUrl = url;
    }

    public void setImageUrl(URL url) {
        try {
            imageUrl = url.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}