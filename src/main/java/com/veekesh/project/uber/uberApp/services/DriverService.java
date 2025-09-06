package com.veekesh.project.uber.uberApp.services;

import com.veekesh.project.uber.uberApp.dto.*;
import com.veekesh.project.uber.uberApp.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
    RideDto acceptRide(Long rideRequestId);
    DriverRideDto cancelRide(Long rideId);
    DriverRideDto startRide(Long rideId, String otp);
    DriverRideDto endRide(Long rideId);
    RiderDto rateRider(Long rideId, Integer rating);
    DriverDto getMyProfile();
    Page<DriverRideDto> getAllMyRides(PageRequest pageRequest);
    Driver getCurrentDriver();
    Driver updateDriverAvailability(Driver driver, boolean available);
    Driver createNewDriver(Driver driver);
    void startJobOfDriver(PointDto pointDto);
}
