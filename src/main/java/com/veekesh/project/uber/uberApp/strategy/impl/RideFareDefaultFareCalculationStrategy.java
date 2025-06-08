package com.veekesh.project.uber.uberApp.strategy.impl;

import com.veekesh.project.uber.uberApp.entities.RideRequest;
import com.veekesh.project.uber.uberApp.services.DistanceService;
import com.veekesh.project.uber.uberApp.strategy.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    public RideFareDefaultFareCalculationStrategy(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());

        return distance*RideFareCalculationStrategy.RIDE_FARE_MULTIPLIER;
    }
}
