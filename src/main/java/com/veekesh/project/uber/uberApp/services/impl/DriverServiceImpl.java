package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.dto.DriverDto;
import com.veekesh.project.uber.uberApp.dto.DriverRideDto;
import com.veekesh.project.uber.uberApp.dto.RiderDto;
import com.veekesh.project.uber.uberApp.entities.Driver;
import com.veekesh.project.uber.uberApp.entities.Ride;
import com.veekesh.project.uber.uberApp.entities.RideRequest;
import com.veekesh.project.uber.uberApp.enums.RideRequestStatus;
import com.veekesh.project.uber.uberApp.enums.RideStatus;
import com.veekesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.veekesh.project.uber.uberApp.repositories.DriverRepository;
import com.veekesh.project.uber.uberApp.services.DriverService;
import com.veekesh.project.uber.uberApp.services.RideRequestService;
import com.veekesh.project.uber.uberApp.services.RideService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    public DriverServiceImpl(RideRequestService rideRequestService, DriverRepository driverRepository, RideService rideService, ModelMapper modelMapper) {
        this.rideRequestService = rideRequestService;
        this.driverRepository = driverRepository;
        this.rideService = rideService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public DriverRideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("RideRequest cannot be accepted, status is "+ rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();
        if (!currentDriver.getAvailable()){
           throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }

        Driver savedDriver = updateDriverAvailability(currentDriver, false);
        Ride ride = rideService.createNewRide(rideRequest,savedDriver);

        return modelMapper.map(ride, DriverRideDto.class);
    }

    @Override
    public DriverRideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier.");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status: " + ride.getRideStatus());
        }

        ride = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver,true);

        return modelMapper.map(ride, DriverRideDto.class);
    }

    @Override
    public DriverRideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier.");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride status is not CONFIRMED hence cannot be started, status : " + ride.getRideStatus());
        }

        if (!otp.equals(ride.getOtp())){
            throw new RuntimeException("OTP is not valid : " + otp);
        }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        return modelMapper.map(savedRide, DriverRideDto.class);
    }

    @Override
    public DriverRideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDto.class);
    }

    @Override
    public Page<DriverRideDto> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver.getId(), pageRequest)
                .map(ride -> modelMapper.map(ride, DriverRideDto.class));
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(()-> new ResourceNotFoundException("Driver not found with id " + 2));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }
}
