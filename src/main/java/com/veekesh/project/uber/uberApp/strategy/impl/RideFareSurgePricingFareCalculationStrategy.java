package com.veekesh.project.uber.uberApp.strategy.impl;

import com.veekesh.project.uber.uberApp.entities.RideRequest;
import com.veekesh.project.uber.uberApp.strategy.RideFareCalculationStrategy;

public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
