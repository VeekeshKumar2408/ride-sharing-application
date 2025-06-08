package com.veekesh.project.uber.uberApp.dto;

public class PointDto {

    private double[] coordinate;
    private String type = "Point";

    public PointDto(double[] coordinate) {
        this.coordinate = coordinate;
    }

    public double[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double[] coordinate) {
        this.coordinate = coordinate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PointDto() {
    }
}
