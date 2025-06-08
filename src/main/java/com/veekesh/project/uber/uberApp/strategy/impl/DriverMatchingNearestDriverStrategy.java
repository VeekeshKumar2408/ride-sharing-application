package com.veekesh.project.uber.uberApp.strategy.impl;

import com.veekesh.project.uber.uberApp.entities.Driver;
import com.veekesh.project.uber.uberApp.entities.RideRequest;
import com.veekesh.project.uber.uberApp.repositories.DriverRepository;
import com.veekesh.project.uber.uberApp.strategy.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    public DriverMatchingNearestDriverStrategy(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
