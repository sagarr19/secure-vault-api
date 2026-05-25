package com.example.secure_vault_api.controller;

import com.example.secure_vault_api.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.secure_vault_api.service.UserService;
import com.example.secure_vault_api.dto.CreateUserRequest;
import com.example.secure_vault_api.dto.UserResponseDTO;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(
        summary = "Create a user",
        description = "Creates a new user with encrypted email and hashed password"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserResponseDTO create(@Valid @RequestBody CreateUserRequest request) {

    return service.save(request);
}
    @Operation(
        summary = "Get all users",
        description = "Returns all registered users"
    )
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<UserResponseDTO> getAll() {
        return service.getAll();
    }
}