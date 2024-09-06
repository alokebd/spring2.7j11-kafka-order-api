package com.kafka.shipping.controller;

import org.springframework.web.bind.annotation.*;

import com.kafka.shipping.model.Shipping;
import com.kafka.shipping.service.ShippedOrderIdProducer;

import java.util.List;

@RestController
@RequestMapping(path = "/shipping")
public class ShippingController {

    private final ShippedOrderIdProducer shippedOrderIdProducer;

    public ShippingController(ShippedOrderIdProducer shippedOrderIdProducer) {
        this.shippedOrderIdProducer = shippedOrderIdProducer;
    }
    
    /*
    @DeleteMapping(path = "/v1/shipped/{orderId}")
    public String produceAndDeleteOrderId(@PathVariable Long orderId){
        shippedOrderIdProducer.produceOrderId(orderId);
        shippedOrderIdProducer.deleteById(orderId);
        return "Order is shipped (deleted) from shippings database and Produced to kafka shipped_order_topic";
    }
    */
    
    @DeleteMapping(path = "/v2/shipped/{orderId}")
    public String produceAndUpdateOrderId(@PathVariable Long orderId){
    	Shipping shipping =shippedOrderIdProducer.findByOrderId(orderId);
    	if (shipping != null && shipping.isPaid()) {
    		shippedOrderIdProducer.produceOrderId(orderId);
    		System.out.println("ShippingController: produced to kafka shipped_order_topic for shipping Id: " + shipping.getId() + ", order id: "+ orderId);
    		
    		shippedOrderIdProducer.deleteById(shipping.getId());
    		return "Order is shipped (deleted) from shippings DB (order id: "+ orderId + " ) and Produced to kafka shipped_order_topic";
    	}
    	return "Order payment has not been processed, please pay";
    }
    

    @GetMapping("/orders")
    public List<Shipping> getAllShippingOrders(){
        return shippedOrderIdProducer.getAllShippingInfo();
    }

    public void saveShipping(Shipping shippingOrder) {
    	if (shippingOrder != null) {
    		if (!shippingOrder.isShipped()) {
	    		Shipping shippingWithId = shippedOrderIdProducer.save(shippingOrder);
	    		System.out.println("ShippingController.saveShipping: shipping id -> " + shippingWithId.getId() );
	    		System.out.println("saved in Shipping DB");
    		}
	    	else {
	    		System.out.println("Shapping is processing for order id: "+  shippingOrder.getId());
	    		
	    	}
    	}
    	else {
    		System.out.println("No record found......");
    	}
    }
}
