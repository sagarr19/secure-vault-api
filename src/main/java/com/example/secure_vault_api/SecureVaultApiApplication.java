package com.example.secure_vault_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class SecureVaultApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureVaultApiApplication.class, args);
	}

}
