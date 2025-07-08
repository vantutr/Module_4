package com.codegym.baitap1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Location {
    @Id
    private String name;
    private String forecast;

    public Location() {
    }

    public Location(String name, String forecast) {
        this.name = name;
        this.forecast = forecast;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }
}
