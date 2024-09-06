package com.kafka.order.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.order.controller.OrderController;
import com.kafka.order.util.AppConstants;

@Service
public class PaymentOrderIdConsumer {
	 @Autowired
	 private OrderController orderController;

	 @KafkaListener(topics = AppConstants.PAYMENT_ORDER_TOPIC)
	 public void consumeOrderId(Long orderId) throws IOException{
	    System.out.println(String.format("##########\nPaymentOrderIdConsumer: Order Service Consumed from kafka payment_order_topic for Order Id-> %s\n##########", orderId));
	    orderController.updatePaidOrder(orderId);
	 }
}
