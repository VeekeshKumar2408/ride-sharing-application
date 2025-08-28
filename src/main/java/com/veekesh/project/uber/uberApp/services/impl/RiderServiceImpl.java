package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.dto.DriverDto;
import com.veekesh.project.uber.uberApp.dto.RideDto;
import com.veekesh.project.uber.uberApp.dto.RideRequestDto;
import com.veekesh.project.uber.uberApp.dto.RiderDto;
import com.veekesh.project.uber.uberApp.entities.Driver;
import com.veekesh.project.uber.uberApp.entities.RideRequest;
import com.veekesh.project.uber.uberApp.entities.Rider;
import com.veekesh.project.uber.uberApp.entities.User;
import com.veekesh.project.uber.uberApp.enums.RideRequestStatus;
import com.veekesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.veekesh.project.uber.uberApp.repositories.RideRequestRepository;
import com.veekesh.project.uber.uberApp.repositories.RiderRepository;
import com.veekesh.project.uber.uberApp.services.RiderService;
import com.veekesh.project.uber.uberApp.strategy.RideStrategyManager;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.WKTReader;
import org.modelmapper.ModelMapper;
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

    public RiderServiceImpl(ModelMapper modelMapper , RideStrategyManager rideStrategyManager, RideRequestRepository rideRequestRepository, RiderRepository riderRepository) {
        this.modelMapper = modelMapper;
        this.rideStrategyManager = rideStrategyManager;
        this.rideRequestRepository = rideRequestRepository;
        this.riderRepository = riderRepository;
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
        rideRequest.setFare(fare);
        rideRequest.setRider(rider);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);

//        TODO : Send notification to all the drivers about this request

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

    @Override
    public Rider getCurrentRider() {
 //    TODO: implement Spring security
        return riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException("Rider not found with id: " + 1));
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
