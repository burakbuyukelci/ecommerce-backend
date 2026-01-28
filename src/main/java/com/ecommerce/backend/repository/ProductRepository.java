package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Anahtar kelimeye gore urun arama (isimde gecen)
    List<Product> findByNameContainingIgnoreCase(String keyword); 
}