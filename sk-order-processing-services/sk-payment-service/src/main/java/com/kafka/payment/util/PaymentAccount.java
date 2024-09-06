package com.kafka.payment.util;

import java.util.HashMap;
import java.util.Map;

public class PaymentAccount {
	private static Map<String, Double> paymentMap = new HashMap<>();

    static {
        paymentMap.put("4724-0000-1111-6566", 12000.00); //VISA
        paymentMap.put("5446-1212-0000-8547", 10000.25); //MASTER-CARD
        paymentMap.put("5510-9999-6501-3939", 5000.05); //DEBIT_CARD
        paymentMap.put("2929-2598-000-7", 8000.00); //PAYPAL
    }


    public static boolean validateCreditLimit(String accNo, double paidAmount) {
        if (paidAmount > paymentMap.get(accNo)) {
            return false;
        } else {
            return true;
        }
    }
}
