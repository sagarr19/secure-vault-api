package com.example.secure_vault_api.repository;

import com.example.secure_vault_api.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {
}