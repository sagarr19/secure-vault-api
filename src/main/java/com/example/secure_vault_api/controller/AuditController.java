package com.example.secure_vault_api.controller;

import com.example.secure_vault_api.model.AuditLog;
import com.example.secure_vault_api.repository.AuditLogRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private final AuditLogRepository repo;

    public AuditController(AuditLogRepository repo) {
        this.repo = repo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<AuditLog> getLogs() {
        return repo.findAll();
    }
}