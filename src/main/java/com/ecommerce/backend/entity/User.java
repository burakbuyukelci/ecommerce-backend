package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data 
@Entity 
@Table(name = "users")
public class User {

    @Id // Birincil anahtar
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID'yi otomatik 1, 2, 3 diye arttir.
    private Long id;

    @Column(nullable = false, unique = true) // Email alani bos olamaz ve benzersiz olmalidir.
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    private String role; // "ADMIN" veya "CUSTOMER" olacak.
}