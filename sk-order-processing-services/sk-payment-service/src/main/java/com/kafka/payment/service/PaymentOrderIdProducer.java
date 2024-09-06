package com.kafka.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.kafka.payment.model.Payment;
import com.kafka.payment.repository.PaymentRepository;
import com.kafka.payment.util.AppConstants;

@Service
public class PaymentOrderIdProducer {

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplateOrderService;
    
   
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment save(Payment payment) {
    	return paymentRepository.save(payment);
    }
    
    public List<Payment> findAllPayments(){
    	return paymentRepository.findAll();
    }
    
    
    public void produceOrderId(Long orderId){
        System.out.println(
                String.format("##########\nPaymentOrderIdProducer: Produced Order Id-> %s\n##########", orderId));
        this.kafkaTemplateOrderService.send(AppConstants.PAYMENT_ORDER_TOPIC, orderId);
    }

}
