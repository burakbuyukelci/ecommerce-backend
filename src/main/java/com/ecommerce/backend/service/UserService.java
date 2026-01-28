package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Yeni kullanici kaydet (Register)
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Email'e gore kullanici bul
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // KULLANICI GIRIS ISLEMI (LOGIN)
    public User loginUser(String email, String password) {
        // 1. Once bu mailde biri var mi diye bak
        User user = userRepository.findByEmail(email).orElse(null);

        // 2. Kullanici varsa VE sifresi dogruysa kullaniciyi geri gonder
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        
        // 3. Yoksa veya sifre yanlissa null don (Giris basarisiz)
        return null;
    }

    // 1. Tum Kullanicilari Getir
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 2. Kullaniciyi Sil (Banla)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}