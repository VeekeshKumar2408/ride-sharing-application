package com.veekesh.project.uber.uberApp.services;

import com.veekesh.project.uber.uberApp.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUserById(Long userId);
}
