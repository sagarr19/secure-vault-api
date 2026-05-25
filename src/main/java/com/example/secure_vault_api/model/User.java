package com.example.secure_vault_api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "users")
public class User {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String username;
private String password;
private String email;    
private String role;

//Default constructor (required by JPA)
public User(){
   
}

//Parameterized constructor
public User(Long id, String username,String password, String email){
    this.id=id;
    this.username=username;
    this.email=email;
    this.password=password;
}

// Getters and setters

public Long getId(){
    return id;
}

public void setId(Long id){
    this.id=id;
}

public String getUsername(){
    return username;
}
public void setUsername(String username){
    this.username=username;
}
public String getPassword(){
    return password;
}
public void setPassword(String password){
    this.password=password;
}

public String getEmail(){
return email;
}

public void setEmail(String email){
    this.email=email;
}
public String getRole(){
    return role;
}
public void setRole(String role){
    this.role=role;
}
}