package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.dto.DriverDto;
import com.veekesh.project.uber.uberApp.dto.SignupDto;
import com.veekesh.project.uber.uberApp.dto.UserDto;
import com.veekesh.project.uber.uberApp.entities.Driver;
import com.veekesh.project.uber.uberApp.entities.User;
import com.veekesh.project.uber.uberApp.enums.Role;
import com.veekesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.veekesh.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.veekesh.project.uber.uberApp.repositories.UserRepository;
import com.veekesh.project.uber.uberApp.services.AuthService;
import com.veekesh.project.uber.uberApp.services.DriverService;
import com.veekesh.project.uber.uberApp.services.RiderService;
import com.veekesh.project.uber.uberApp.services.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;

    public AuthServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RiderService riderService, WalletService walletService, DriverService driverService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.riderService = riderService;
        this.walletService = walletService;
        this.driverService = driverService;
    }

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDTO) {
        userRepository.findByEmail(signupDTO.getEmail()).orElseThrow(()-> new RuntimeConflictException("Cannot signup. User already exists with email"));
        User mappedUser = modelMapper.map(signupDTO, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        // creating user related entities
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        if (user.getRoles().contains(Role.DRIVER))
            throw new RuntimeConflictException("User with id " + userId + " is already a Driver");

        Driver createDriver = Driver.builder()
                .user(user)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }
}
