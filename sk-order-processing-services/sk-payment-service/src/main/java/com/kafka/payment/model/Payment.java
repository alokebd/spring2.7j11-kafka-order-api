package com.kafka.payment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sk_payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerCardNumber;
    private double amount;
	private String customerName;
	private Long orderId;
 

	public Payment() {
    }

    public Payment(Long id, String customerCardNumber, double amount, String customerName) {
        this.id = id;
        this.customerCardNumber = customerCardNumber;
        this.amount = amount;
        this.customerName = customerName;
    }

    public Payment(String customerCardNumber, double amount, String customerName) {
        this.customerCardNumber = customerCardNumber;
        this.amount = amount;
        this.customerName = customerName;

    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCardNumber() {
        return customerCardNumber;
    }

    public void setCustomerCardNumber(String customerCardNumber) {
        this.customerCardNumber = customerCardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double totalCost) {
        this.amount = totalCost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
