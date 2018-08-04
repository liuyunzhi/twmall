package com.thoughtworks.thoughtworks_mall.controller;

import com.thoughtworks.thoughtworks_mall.controller.request.OrderRequest;
import com.thoughtworks.thoughtworks_mall.entity.Order;
import com.thoughtworks.thoughtworks_mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.get(id));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody OrderRequest orderRequest) {
        Order newOrder = orderService.add(orderRequest);
        return ResponseEntity.created(URI.create("/" + newOrder.getId())).build();
    }

    @PostMapping("/{id}/orderItems")
    public ResponseEntity addProductTo(@PathVariable Long id, @RequestParam Long productId) {
        orderService.addProductTo(id, productId);
        return ResponseEntity.created(URI.create("/" + id + "/orderItems/")).build();
    }
}
