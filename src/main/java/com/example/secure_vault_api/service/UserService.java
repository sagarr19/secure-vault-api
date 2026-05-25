package com.example.secure_vault_api.service;

import com.example.secure_vault_api.model.User;
import com.example.secure_vault_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.secure_vault_api.util.CryptoUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import com.example.secure_vault_api.dto.UserResponseDTO;
import com.example.secure_vault_api.dto.CreateUserRequest;
import java.util.stream.Collectors;
import com.example.secure_vault_api.exception.UserAlreadyExistsException;

@Service
public class UserService{
    
   private final UserRepository repo;
   private final CryptoUtil cryptoUtil;
   private final PasswordEncoder passwordEncoder;
   private final AuditService auditService;
  
    public UserService(UserRepository repo, CryptoUtil cryptoUtil, PasswordEncoder passwordEncoder, AuditService auditService) {
    this.repo = repo;
    this.cryptoUtil = cryptoUtil;
    this.passwordEncoder=passwordEncoder;
    this.auditService=auditService;
}
    
    public UserResponseDTO save(CreateUserRequest request){
        
        if (repo.existsByUsername(request.getUsername())) {
        throw new UserAlreadyExistsException(
            "Username already exists"
        );
    }
        User user = new User();
        
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        
        String encryptedEmail = cryptoUtil.encrypt(user.getEmail());
        user.setEmail(encryptedEmail);
        
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        
        user.setPassword(hashedPassword);
        
        User savedUser = repo.save(user);
        
        auditService.log(
        savedUser.getUsername(),
        "USER_CREATED"
);
        
        return new UserResponseDTO(
        user.getId(),
        user.getUsername(),
        cryptoUtil.decrypt(user.getEmail())
);
    }
    public List<UserResponseDTO> getAll() {

    return repo.findAll()
            .stream()
            .map(user -> new UserResponseDTO(
                    user.getId(),
                    user.getUsername(),
                    cryptoUtil.decrypt(user.getEmail())
            ))
            .collect(Collectors.toList());
}
}