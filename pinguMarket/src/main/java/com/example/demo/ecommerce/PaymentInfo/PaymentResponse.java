package com.example.demo.ecommerce.PaymentInfo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponse {
    private String paymentKey;
    private String orderId;
    private Long amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime paymentDate;
}