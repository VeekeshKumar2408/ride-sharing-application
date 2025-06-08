package com.veekesh.project.uber.uberApp.strategy.impl;

import com.veekesh.project.uber.uberApp.dto.RideRequestDto;
import com.veekesh.project.uber.uberApp.entities.Driver;
import com.veekesh.project.uber.uberApp.entities.RideRequest;
import com.veekesh.project.uber.uberApp.strategy.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingHighestRateDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return List.of();
    }
}
