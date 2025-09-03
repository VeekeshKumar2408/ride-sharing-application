package com.veekesh.project.uber.uberApp.services.impl;

import com.veekesh.project.uber.uberApp.entities.User;
import com.veekesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.veekesh.project.uber.uberApp.repositories.UserRepository;
import com.veekesh.project.uber.uberApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with email is not present : " + username));
    }

    public User findUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with userId is not present : " + userId));
    }
}
