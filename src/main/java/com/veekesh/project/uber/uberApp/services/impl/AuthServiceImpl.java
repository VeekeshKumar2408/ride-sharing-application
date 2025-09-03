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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RiderService riderService, WalletService walletService, DriverService driverService, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.riderService = riderService;
        this.walletService = walletService;
        this.driverService = driverService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String[] login(String email, String password) {
        String[] tokens = new String[2];
        return null;
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDTO) {
        Optional<User> existingUser = userRepository.findByEmail(signupDTO.getEmail());
        if (existingUser.isPresent()) throw new RuntimeConflictException("Cannot signup. User already exists with email : " + signupDTO.getEmail());

        User mappedUser = modelMapper.map(signupDTO, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
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
