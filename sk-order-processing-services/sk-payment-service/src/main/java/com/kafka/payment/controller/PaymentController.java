package com.kafka.payment.controller;

import org.springframework.web.bind.annotation.*;
import com.kafka.payment.model.Payment;
import com.kafka.payment.service.PaymentOrderIdProducer;
import com.kafka.payment.util.PaymentAccount;

import java.util.List;

@RestController
@RequestMapping(path = "/payments")
public class PaymentController {

    private final PaymentOrderIdProducer paymentOrderIdProducer;

    public PaymentController(PaymentOrderIdProducer shippedOrderIdProducer) {
        this.paymentOrderIdProducer = shippedOrderIdProducer;
    }

    @PostMapping(path = "/order/{orderId}")
    public String produceAndDeleteOrderId(@PathVariable Long orderId, @RequestBody Payment payment){
    	boolean valid = PaymentAccount.validateCreditLimit(payment.getCustomerCardNumber(), payment.getAmount());
    	if (valid) {
    		Payment dto = new Payment(payment.getCustomerCardNumber(), payment.getAmount(), payment.getCustomerName());
    		dto.setOrderId(orderId);
    		Payment paymentWithId = paymentOrderIdProducer.save(dto);
    		
    		System.out.println("saved Payment DB paymentId ->" + paymentWithId.getId() + ", orderId ->"+paymentWithId.getOrderId());
    		
    		paymentOrderIdProducer.produceOrderId(orderId);
    		return "Successfully paid "+ payment.getAmount() + " for order: "+ orderId;
    	}
    	return "Insufficient payment"; 
       
    }

    @GetMapping
    public List<Payment> getOrdersToShipping(){
        return paymentOrderIdProducer.findAllPayments();
    }

    public void paid(Payment payment) {
    	paymentOrderIdProducer.save(payment);
        System.out.println("saved in Payment DB");
    }
}
