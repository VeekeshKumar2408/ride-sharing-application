package com.veekesh.project.uber.uberApp.controller;

import com.veekesh.project.uber.uberApp.dto.*;
import com.veekesh.project.uber.uberApp.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnboardDriverDto onboardDriverDto){
        return new ResponseEntity<>(authService.onboardNewDriver(userId, onboardDriverDto.getVehicleId()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        String[] token = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return ResponseEntity.ok(new LoginResponseDto(token[0]));
    }
}
