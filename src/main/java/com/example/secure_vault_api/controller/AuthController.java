package com.example.secure_vault_api.controller;

import com.example.secure_vault_api.dto.AuthRequest;
import com.example.secure_vault_api.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import com.example.secure_vault_api.service.AuditService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuditService auditService;

    public AuthController(JwtUtil jwtUtil, AuditService auditService) {
        this.jwtUtil = jwtUtil;
        this.auditService = auditService;
        
        System.out.println("AUTH CONTROLLER LOADED!");
    }
    @Operation(
    summary = "Generate JWT token",
    description = "Authenticates user and returns JWT token"
)

    @PostMapping("/login")
public String login(@RequestBody AuthRequest request) {

    if ("admin".equals(request.getUsername())
            && "admin123".equals(request.getPassword())) {
        
        auditService.log(
        request.getUsername(),
        "LOGIN_SUCCESS"
);

        return jwtUtil.generateToken(
                request.getUsername(),
                "ADMIN"
        );
    }
    
    auditService.log(
        request.getUsername(),
        "LOGIN_FAILED"
);
    throw new RuntimeException("Invalid credentials");
}
}