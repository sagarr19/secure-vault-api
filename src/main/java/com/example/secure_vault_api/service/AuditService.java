package com.example.secure_vault_api.service;

import com.example.secure_vault_api.model.AuditLog;
import com.example.secure_vault_api.repository.AuditLogRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final AuditLogRepository repo;

    public AuditService(AuditLogRepository repo) {
        this.repo = repo;
    }

    public void log(String username,
                    String action) {

        AuditLog auditLog = new AuditLog();

        auditLog.setUsername(username);
        auditLog.setAction(action);
        auditLog.setTimestamp(LocalDateTime.now());

        repo.save(auditLog);
    }
}