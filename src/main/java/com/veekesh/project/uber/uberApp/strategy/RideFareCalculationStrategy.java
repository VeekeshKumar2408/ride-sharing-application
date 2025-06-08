package com.veekesh.project.uber.uberApp.strategy;

import com.veekesh.project.uber.uberApp.entities.RideRequest;

public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);
}
