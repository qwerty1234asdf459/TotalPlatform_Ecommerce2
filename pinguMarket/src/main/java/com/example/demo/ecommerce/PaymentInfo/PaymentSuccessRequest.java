package com.example.demo.ecommerce.PaymentInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentSuccessRequest {
    private String paymentKey;
    private String orderId;
    private Long amount;
}