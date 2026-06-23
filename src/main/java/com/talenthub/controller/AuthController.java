package com.talenthub.controller;

import com.talenthub.dto.ApiResponse;
import com.talenthub.dto.LoginRequest;
import com.talenthub.dto.LoginResponse;
import com.talenthub.dto.RegisterRequest;
import com.talenthub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse registerUser(
            @Valid @RequestBody RegisterRequest request) {

        System.out.println("Register API Hit");

        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(
            @RequestBody LoginRequest request) {

        System.out.println("Login API Hit");

        return userService.loginUser(request);
    }
}