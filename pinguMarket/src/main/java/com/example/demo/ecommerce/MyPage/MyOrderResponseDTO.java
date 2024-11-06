package com.example.demo.ecommerce.MyPage;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Payment.PaymentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyOrderResponseDTO {
    private List<PaymentDTO> payments;
    
    private List<String> productNames;
    
    private List<Integer> totalPrices;
    
    public MyOrderResponseDTO(List<Payment> payments, List<String> productNames, List<Integer> totalPrices) {
        this.payments = payments.stream().map(PaymentDTO::new).collect(Collectors.toList());
        this.productNames = productNames;
        this.totalPrices = totalPrices;
    }
}