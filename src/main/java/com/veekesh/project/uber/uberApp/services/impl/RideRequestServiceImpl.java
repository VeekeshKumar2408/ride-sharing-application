package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.entities.RideRequest;
import com.veekesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.veekesh.project.uber.uberApp.repositories.RideRequestRepository;
import com.veekesh.project.uber.uberApp.services.RideRequestService;
import org.springframework.stereotype.Service;

@Service
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepository rideRequestRepository;

    public RideRequestServiceImpl(RideRequestRepository rideRequestRepository) {
        this.rideRequestRepository = rideRequestRepository;
    }

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id " + rideRequest.getId()));

        rideRequestRepository.save(rideRequest);
    }
}
