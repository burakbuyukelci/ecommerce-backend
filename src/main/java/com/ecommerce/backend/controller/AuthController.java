package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200") // Angular'a izin ver
@RestController
@RequestMapping("/api/auth") // Adresimiz: http://localhost:8080/api/auth
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // KAYIT OL (Register)
    // Adres: POST http://localhost:8080/api/auth/register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // GIRIS YAP (Login)
    // Adres: POST http://localhost:8080/api/auth/login
    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        // Email ve Sifre ile kullanici giris islemi
        User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        
        if (user != null) {
            return user; // Giris basarili
        } else {
            throw new RuntimeException("Email veya Şifre Hatalı!"); // Hata firlat
        }
    }

    // Tum Kullanicilari Getir
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Kullanici Sil (Banla)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Kullanıcı silindi!");
    }
}