package com.veekesh.project.uber.uberApp.controller;

import com.veekesh.project.uber.uberApp.dto.DriverRideDto;
import com.veekesh.project.uber.uberApp.dto.RideDto;
import com.veekesh.project.uber.uberApp.dto.RideStartDto;
import com.veekesh.project.uber.uberApp.services.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<DriverRideDto> acceptRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<DriverRideDto> startRide(@PathVariable Long rideRequestId,
                                             @RequestBody RideStartDto rideStartDto){
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));
    }
}
