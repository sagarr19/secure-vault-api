/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.secure_vault_api.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class CryptoUtil {
    
    private static final String ALGO = "AES";
    private static final byte[] KEY = 
            "12345678901234567890123456789012".getBytes(); //32 bytes = AES-256
    
    private final SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGO);
    
    public String encrypt (String data){
        try{
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE,keySpec);
            
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }catch(Exception e){
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    public String decrypt(String encryptedData){
        try{
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            return new String(cipher.doFinal(decoded));
        }catch (Exception e){
            throw new RuntimeException("Decryption failed", e);
        }
    }
}
