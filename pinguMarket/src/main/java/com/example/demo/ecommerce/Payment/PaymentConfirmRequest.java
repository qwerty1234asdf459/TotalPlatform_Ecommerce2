package com.example.demo.ecommerce.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class PaymentConfirmRequest {
    private String paymentKey;
    private String orderId;
    private Long amount;
}
