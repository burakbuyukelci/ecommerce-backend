package com.ecommerce.backend.entity; 

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders") 
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName; 
    
    @Column(length = 1000)
    private String productList; 
    
    private Double totalPrice;

    private LocalDateTime orderDate; 

    public Order() {
        this.orderDate = LocalDateTime.now(); // Siparis olusturuldugunda tarih otomatik atanir
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getProductList() { return productList; }
    public void setProductList(String productList) { this.productList = productList; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getOrderDate() { return orderDate; }
}