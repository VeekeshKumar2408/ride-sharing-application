package com.veekesh.project.uber.uberApp.controller;

import com.veekesh.project.uber.uberApp.dto.RideRequestDto;
import com.veekesh.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
public class RiderController {

    private final RiderService riderService;
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }
    @PostMapping("/requestRide")
    public ResponseEntity<?> requestRide(@RequestBody RideRequestDto rideRequestDto){
        return new ResponseEntity<>(riderService.requestRide(rideRequestDto), HttpStatus.OK);
    }
}
