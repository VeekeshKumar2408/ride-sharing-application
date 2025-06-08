package com.veekesh.project.uber.uberApp.services;

import com.veekesh.project.uber.uberApp.dto.DriverDto;
import com.veekesh.project.uber.uberApp.dto.RideDto;
import com.veekesh.project.uber.uberApp.dto.RideRequestDto;
import com.veekesh.project.uber.uberApp.dto.RiderDto;
import com.veekesh.project.uber.uberApp.entities.Rider;
import com.veekesh.project.uber.uberApp.entities.User;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RideDto cancelRide(Long rideId);
    DriverDto rateDriver(Long rideId, Integer rating);
    RiderDto getMyProfile();
    List<RideDto> getAllMyRides();
    Rider createNewRider(User user);
}
