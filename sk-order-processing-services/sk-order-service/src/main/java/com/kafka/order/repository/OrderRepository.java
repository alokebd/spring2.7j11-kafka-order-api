package com.kafka.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
