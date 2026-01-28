package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Bu sinifin bir Service (Is Katmani) oldugunu belirtir.
public class ProductService {

    // Dependency Injection: ProductRepository'i buraya enjekte ediyoruz.
    private final ProductRepository productRepository;

    // Constructor Injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 1. Tüm ürünleri getir
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. Yeni ürün kaydet
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    // 3. ID'ye göre ürün bul
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // ID'si verilen urunu silme islemi
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Stok Guncelleme Mantigi (Burasi Depocu ile konusur)
    public Product updateStock(Long id, int quantity) {
        // 1. Urunu bul
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));
        
        // 2. Yeni stogu hesapla
        int newStock = product.getStock() - quantity;
        
        // 3. Eksiye dusmesin kontrolu
        if (newStock < 0) {
            throw new RuntimeException("Stok yetersiz!");
        }
        
        // 4. Kaydet
        product.setStock(newStock);
        return productRepository.save(product);
    }
}