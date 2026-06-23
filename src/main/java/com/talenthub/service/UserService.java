package com.talenthub.service;

import com.talenthub.dto.ApiResponse;
import com.talenthub.dto.LoginRequest;
import com.talenthub.dto.LoginResponse;
import com.talenthub.dto.RegisterRequest;
import com.talenthub.entity.User;
import com.talenthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.talenthub.security.JwtUtil;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            return new ApiResponse(
                    false,
                    "Email already exists"
            );
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(request.getRole());

        userRepository.save(user);

        return new ApiResponse(
                true,
                "User Registered Successfully"
        );
    }

    public LoginResponse loginUser(LoginRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(
                        request.getEmail()
                );

        if (optionalUser.isEmpty()) {

            return new LoginResponse(
                    false,
                    "Invalid Email",
                    null
            );
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return new LoginResponse(
                    false,
                    "Invalid Password",
                    null
            );
        }

        String token =
                jwtUtil.generateToken(
                        user.getEmail()
                );

        return new LoginResponse(
                true,
                "Login Successful",
                token
        );
    }
}