package com.sassenach.backendexample.entities;

public class Response {
    private Double avgTemperature;
    private Double sumTemperature;
    private Double minTemperature;
    private Double maxTemperature;

    public Response(Double avgTemperature, Double sumTemperature, Double minTemperature, Double maxTemperature) {
        super();
        this.avgTemperature = avgTemperature;
        this.sumTemperature = sumTemperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
}
