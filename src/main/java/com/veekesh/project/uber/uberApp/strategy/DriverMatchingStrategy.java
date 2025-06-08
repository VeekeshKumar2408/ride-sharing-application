package com.veekesh.project.uber.uberApp.strategy;

import com.veekesh.project.uber.uberApp.entities.Driver;
import com.veekesh.project.uber.uberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
