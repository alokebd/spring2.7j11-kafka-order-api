package com.kafka.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.order.controller.OrderController;
import com.kafka.order.util.AppConstants;

import java.io.IOException;

@Service
public class ShippedOrderIdConsumer {

    @Autowired
    private OrderController orderController;

    @KafkaListener(topics = AppConstants.SHIPPED_ORDER_TOPIC)
    public void consumeOrderId(Long orderId) throws IOException{
        System.out.println(String.format("##########\nShippedOrderIdConsumer: Consumed from kafka shipped_order_topic for Order Id-> %s\n##########", orderId));
        orderController.updateShippedOrder(orderId);
    }

}
