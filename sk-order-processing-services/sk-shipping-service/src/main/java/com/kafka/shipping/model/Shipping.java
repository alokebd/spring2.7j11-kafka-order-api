package com.kafka.shipping.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sk_shipping")
public class Shipping {
    @Id
    private Long id;
    private String customerName;
    private double totalCost;
    private String address;
    private boolean shipped;
    private boolean paid;

   	public Shipping() {
    }

    public Shipping(Long id, String customerName, double totalCost, String address, boolean shipped, boolean paid) {
        this.id = id;
        this.customerName = customerName;
        this.totalCost = totalCost;
        this.address = address;
        this.shipped = shipped;
        this.paid = paid;
    }

    public Shipping(String customerName, double totalCost, String address, boolean shipped, boolean paid) {
        this.customerName = customerName;
        this.totalCost = totalCost;
        this.address = address;
        this.shipped = shipped;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }
    
    public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
