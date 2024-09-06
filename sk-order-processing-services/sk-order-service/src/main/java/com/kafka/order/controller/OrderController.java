package com.kafka.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kafka.order.model.Order;
import com.kafka.order.service.OrderProducer;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final  OrderProducer orderProducer;

    @Autowired
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping(path = "/checkout")
    public String saveAndProduceOrder(@RequestBody Order order){
        Order orderWithId = orderProducer.saveOrder(order);
        System.out.println("OrderController (/checkout): saved order id: "+ orderWithId.getId() + " produced to kafka order_topic");
        orderProducer.produceOrder(orderWithId);
        return "Saved in orders DB (order id: "+ orderWithId.getId() + ") and produced to kafka order_topic";
    }
        
    public void updatePaidOrder(Long orderId) {
    	 Order orderWithId = orderProducer.findPaidOrder(orderId);
    	 orderProducer.produceOrder(orderWithId);
    	 System.out.println("OrderController.updatePaidOrder: Paid order Id-> " + orderWithId.getId() + " produced to kafka order_topic");
         System.out.println("OrderController: Order is Paid");
    }
    
    public void updateShippedOrder(Long orderId) {
        Order orderWithId = orderProducer.findShippedOrder(orderId);
        System.out.println("OrderController.updateShippedOrder:  Shipped order Id -> " + orderWithId.getId());
        orderProducer.produceOrder(orderWithId);
        System.out.println("OrderController:  Order is shipping updated");
    }
    
    
}
