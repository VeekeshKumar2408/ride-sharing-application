package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.dto.DriverDto;
import com.veekesh.project.uber.uberApp.dto.RideDto;
import com.veekesh.project.uber.uberApp.dto.RideRequestDto;
import com.veekesh.project.uber.uberApp.dto.RiderDto;
import com.veekesh.project.uber.uberApp.entities.RideRequest;
import com.veekesh.project.uber.uberApp.entities.Rider;
import com.veekesh.project.uber.uberApp.entities.User;
import com.veekesh.project.uber.uberApp.enums.RideRequestStatus;
import com.veekesh.project.uber.uberApp.repositories.RideRequestRepository;
import com.veekesh.project.uber.uberApp.repositories.RiderRepository;
import com.veekesh.project.uber.uberApp.services.RiderService;
import com.veekesh.project.uber.uberApp.strategy.DriverMatchingStrategy;
import com.veekesh.project.uber.uberApp.strategy.RideFareCalculationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    public RiderServiceImpl(ModelMapper modelMapper, RideFareCalculationStrategy rideFareCalculationStrategy, DriverMatchingStrategy driverMatchingStrategy, RideRequestRepository rideRequestRepository, RiderRepository riderRepository) {
        this.modelMapper = modelMapper;
        this.rideFareCalculationStrategy = rideFareCalculationStrategy;
        this.driverMatchingStrategy = driverMatchingStrategy;
        this.rideRequestRepository = rideRequestRepository;
        this.riderRepository = riderRepository;
    }

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        driverMatchingStrategy.findMatchingDrivers(rideRequest);
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = new Rider(user,0.0);
        return riderRepository.save(rider);
    }
}
