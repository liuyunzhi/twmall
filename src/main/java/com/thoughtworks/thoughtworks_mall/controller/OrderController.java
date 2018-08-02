package com.thoughtworks.thoughtworks_mall.controller;

import com.thoughtworks.thoughtworks_mall.entity.Order;
import com.thoughtworks.thoughtworks_mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.get(id));
    }
}
