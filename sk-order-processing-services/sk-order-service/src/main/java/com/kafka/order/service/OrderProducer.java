package com.kafka.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.order.model.Order;
import com.kafka.order.repository.OrderRepository;
import com.kafka.order.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Order saveOrder(Order order) {
    	return orderRepository.save(order);
    }
    
    public Order findShippedOrder(Long orderId) {
    	Order order = orderRepository.findById(orderId).get();
    	order.setShipped(true);
    	return orderRepository.save(order);
  
    }

    public Order findPaidOrder(Long orderId) {
    	Order order = orderRepository.findById(orderId).get();
    	order.setPaid(true);
    	//order.setShipped(true);
    	return orderRepository.save(order);
  
    }
    
    public void produceOrder(Order order){
        ObjectMapper mapper = new ObjectMapper();
        String orderJsonString = null;
        try {
            orderJsonString = mapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(
                String.format("##########\nOrderProducer: Produced to kafak order_topic for Order-> %s\n##########", orderJsonString));
        this.kafkaTemplate.send(AppConstants.ORDER_TOPIC, orderJsonString);
    }
}
