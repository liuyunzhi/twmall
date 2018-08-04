package com.thoughtworks.thoughtworks_mall.service;

import com.thoughtworks.thoughtworks_mall.controller.request.OrderRequest;
import com.thoughtworks.thoughtworks_mall.entity.Order;
import com.thoughtworks.thoughtworks_mall.entity.OrderItem;
import com.thoughtworks.thoughtworks_mall.entity.Product;
import com.thoughtworks.thoughtworks_mall.repository.OrderItemRepository;
import com.thoughtworks.thoughtworks_mall.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

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

    public Order add(OrderRequest orderRequest) {
        List<OrderItem> orderItems = new ArrayList<>();

        orderRequest.getOrderItemRequests().forEach(orderItem -> {
            OrderItem newOrderItem = new OrderItem();
            Product product = productService.get(orderItem.getProductId());
            newOrderItem.setProduct(product);
            newOrderItem.setCount(orderItem.getCount());
            orderItems.add(newOrderItem);
        });
        Order order = new Order();
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    public void addProductTo(Long id, Long productId) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            Order order = optional.get();
            List<OrderItem> orderItems = order.getOrderItems();
            for (int i = 0; i < orderItems.size(); i++) {
                if (orderItems.get(i).getProduct().getId() == productId) {
                    orderItems.get(i).setCount(orderItems.get(i).getCount() + 1);
                    break;
                }
            }
        }
    }
}
