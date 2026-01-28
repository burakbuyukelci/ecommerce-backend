package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Order;
import com.ecommerce.backend.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200") // Angular'a izin ver
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 1. Yeni Siparis Olustur
    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    // 2. Tum Siparisleri Getir (Admin icin)
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByOrderDateDesc();
    }

    // 3. Kullaniciya Ait Siparisleri Getir
    @GetMapping("/my-orders")
    public List<Order> getUserOrders(@RequestParam String username) {
        return orderRepository.findAllByCustomerNameOrderByOrderDateDesc(username);
    }
}