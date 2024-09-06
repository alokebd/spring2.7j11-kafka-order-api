package com.kafka.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.shipping.model.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
