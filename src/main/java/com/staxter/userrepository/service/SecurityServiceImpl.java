package com.staxter.userrepository.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public String hashPassword(String plainPassword) {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(plainPassword.getBytes(StandardCharsets.UTF_8));

            return new String(hashedPassword);

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
