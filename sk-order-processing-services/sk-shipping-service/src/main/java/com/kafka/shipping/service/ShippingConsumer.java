package com.kafka.shipping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.shipping.controller.ShippingController;
import com.kafka.shipping.model.Shipping;
import com.kafka.shipping.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShippingConsumer {

    @Autowired
    private ShippingController shippingController;

    @KafkaListener(topics = AppConstants.ORDER_TOPIC)
    public void consumeOrder(String shippingOrderString) throws IOException {
        System.out.println(String.format("##########\nShippingConsumer: Consumed Order-> %s\n##########", shippingOrderString));
        ObjectMapper mapper = new ObjectMapper();
        Shipping shippingOrder = mapper.readValue(shippingOrderString, Shipping.class);
        shippingController.saveShipping(shippingOrder);
    }
}
