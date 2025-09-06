package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.dto.*;
import com.veekesh.project.uber.uberApp.entities.*;
import com.veekesh.project.uber.uberApp.enums.RideRequestStatus;
import com.veekesh.project.uber.uberApp.enums.RideStatus;
import com.veekesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.veekesh.project.uber.uberApp.repositories.RideRequestRepository;
import com.veekesh.project.uber.uberApp.repositories.RiderRepository;
import com.veekesh.project.uber.uberApp.services.DriverService;
import com.veekesh.project.uber.uberApp.services.RatingService;
import com.veekesh.project.uber.uberApp.services.RideService;
import com.veekesh.project.uber.uberApp.services.RiderService;
import com.veekesh.project.uber.uberApp.strategy.RideStrategyManager;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    public RiderServiceImpl(ModelMapper modelMapper , RideStrategyManager rideStrategyManager, RideRequestRepository rideRequestRepository, RiderRepository riderRepository, RideService rideService, DriverService driverService, RatingService ratingService) {
        this.modelMapper = modelMapper;
        this.rideStrategyManager = rideStrategyManager;
        this.rideRequestRepository = rideRequestRepository;
        this.riderRepository = riderRepository;
        this.rideService = rideService;
        this.driverService = driverService;
        this.ratingService = ratingService;
    }

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        Point pickupPoint = createPointFromCoordinates(rideRequestDto.getPickupLocation().getCoordinates());
        rideRequest.setPickupLocation(pickupPoint);

        Point dropOffPoint = createPointFromCoordinates(rideRequestDto.getDropOffLocation().getCoordinates());
        rideRequest.setDropOffLocation(dropOffPoint);

        Double fare =rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);

        rideRequest.setFare(Math.ceil(fare));
        rideRequest.setRider(rider);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
//        TODO : Send notification to all the drivers about this request

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if (rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not own this ride with id: " + rideId);
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled, invalid status: " + ride.getRideStatus());
        }

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();

        if (!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider is not the owner ride.");
        }

        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException("Ride status is not ENDED hence cannot start rating : " + ride.getRideStatus());
        }

        return ratingService.rateDriver(ride,rating);
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider, pageRequest)
                .map(ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = new Rider(user, 0.0);
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return riderRepository.findByUser(user1)
                .orElseThrow(()-> new ResourceNotFoundException("Rider not associated with user with id: " + 1));
    }

    public static Point createPointFromCoordinates(double[] coordinates) {
        if (coordinates == null || coordinates.length < 2) {
            throw new IllegalArgumentException("Coordinates must be [longitude, latitude]");
        }

        double longitude = coordinates[0];
        double latitude = coordinates[1];

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }

}
