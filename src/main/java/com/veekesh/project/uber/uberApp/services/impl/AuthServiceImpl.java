package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.dto.DriverDto;
import com.veekesh.project.uber.uberApp.dto.SignupDto;
import com.veekesh.project.uber.uberApp.dto.UserDto;
import com.veekesh.project.uber.uberApp.entities.User;
import com.veekesh.project.uber.uberApp.enums.Role;
import com.veekesh.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.veekesh.project.uber.uberApp.repositories.RiderRepository;
import com.veekesh.project.uber.uberApp.repositories.UserRepository;
import com.veekesh.project.uber.uberApp.services.AuthService;
import com.veekesh.project.uber.uberApp.services.RiderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    public AuthServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RiderService riderService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.riderService = riderService;
    }

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDTO) {
        userRepository.findByEmail(signupDTO.getEmail()).orElseThrow(()-> new RuntimeConflictException("Cannot signup. User already exists with email"));
        User mappedUser = modelMapper.map(signupDTO, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        // creating user related entities
        riderService.createNewRider(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
