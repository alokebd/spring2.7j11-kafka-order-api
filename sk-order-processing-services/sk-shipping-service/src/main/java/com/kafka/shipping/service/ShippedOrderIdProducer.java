package com.kafka.shipping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.shipping.model.Shipping;
import com.kafka.shipping.repository.ShippingRepository;
import com.kafka.shipping.util.AppConstants;

@Service
public class ShippedOrderIdProducer {

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;
    
    @Autowired
    private ShippingRepository shippingRepository;
    
    public List<Shipping> getAllShippingInfo(){
    	 return shippingRepository.findAll();
    }
    
    public Shipping save(Shipping shippingOrder) {
    	 return shippingRepository.save(shippingOrder);
    }
    
    public void deleteById(Long id ) {
    	 shippingRepository.deleteById(id);
    }
    
    public Shipping findByOrderId(Long orderId) {
    	return shippingRepository.findById(orderId).get();
    	
    }
    
    public void update(Shipping shipping) {
   	  shippingRepository.save(shipping);
   }
    
    public void produceOrderId(Long orderId){
        System.out.println(
                String.format("##########\nShippedOrderIdProducer: Produced to kafka shipped_order_topic for Order Id-> %s\n##########", orderId));
        this.kafkaTemplate.send(AppConstants.SHIPPED_ORDER_TOPIC, orderId);
    }


}
