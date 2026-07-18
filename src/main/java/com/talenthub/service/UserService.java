package com.talenthub.service;

import com.talenthub.dto.*;
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
                    null,
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
                    null,
                    null
            );
        }

        String token =
                jwtUtil.generateToken(
                        user.getEmail()
                );

        String frontendRole;

        switch (user.getRole()) {

            case "JOB_SEEKER":
                frontendRole = "candidate";
                break;

            case "RECRUITER":
                frontendRole = "recruiter";
                break;

            case "ADMIN":
                frontendRole = "admin";
                break;

            default:
                frontendRole = "candidate";
        }

        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                frontendRole
        );

        return new LoginResponse(
                true,
                "Login Successful",
                token,
                userDto
        );
    }
}