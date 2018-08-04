package com.thoughtworks.thoughtworks_mall.repository;

import com.thoughtworks.thoughtworks_mall.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
