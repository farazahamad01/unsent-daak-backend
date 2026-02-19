package com.theunsentdaak.api.service;

import com.theunsentdaak.api.dto.Dtos;
import com.theunsentdaak.api.model.Admin;
import com.theunsentdaak.api.repository.AdminRepository;
import com.theunsentdaak.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public Dtos.LoginResponse login(Dtos.LoginRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(admin.getEmail());
        return new Dtos.LoginResponse(token, admin.getEmail());
    }
}
