package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.service.ProductService;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController // Bu sinif bir REST API denetleyicisidir.
@RequestMapping("/api/products") // Adresimiz: http://localhost:8080/api/products
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    // GET istegi gelirse calisir: Tum urunleri getirir.
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // POST istegi gelirse calisir: Yeni urun ekler.
    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        // ID'yi null yap ki veritabani yeni urun olarak algilasin.
        // Eger ID dolu olursa, veritabani bunu guncelleme islemi sanabilir.
        product.setId(null);

        return productService.saveProduct(product);
    }

    

    // DELETE istegi gelirse calisir: Urunu siler.
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    
    // Stok Guncelleme Kapisi
    @PutMapping("/{id}/stock")
    public Product updateStock(@PathVariable Long id, @RequestParam int quantity) {
        // Stok guncelleme islemini ProductService'e devret
        return productService.updateStock(id, quantity);
    }
    // Urun Arama Kapisi
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}