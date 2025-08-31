package com.veekesh.project.uber.uberApp.controller;

import com.veekesh.project.uber.uberApp.dto.*;
import com.veekesh.project.uber.uberApp.services.RiderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<?> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver")
    public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(riderService.rateDriver(ratingDto.getRideId(), ratingDto.getRating()));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDto> getMyProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffSet,
                                                       @RequestParam(defaultValue = "10", required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffSet, pageSize, Sort.by(Sort.Direction.DESC, "createdTime","id"));
        return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
    }
}
