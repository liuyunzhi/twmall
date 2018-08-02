package com.thoughtworks.thoughtworks_mall.service;

import com.thoughtworks.thoughtworks_mall.entity.Order;
import com.thoughtworks.thoughtworks_mall.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order get(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        double totalPrice = 0.0;
        if (order != null) {
            for (int i = 0; i < order.getOrderItems().size(); i++) {
                totalPrice += order.getOrderItems().get(i).getCount() * order.getOrderItems().get(i).getProduct().getPrice();
            }
            order.setTotalPrice(totalPrice);
        }
        return order;
    }

    public Order add(Order order) {
        return orderRepository.save(order);
    }
}
