package com.veekesh.project.uber.uberApp.strategy;

import com.veekesh.project.uber.uberApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDto rideRequestDto);
}
