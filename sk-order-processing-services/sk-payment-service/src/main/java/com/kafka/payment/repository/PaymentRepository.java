package com.kafka.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
