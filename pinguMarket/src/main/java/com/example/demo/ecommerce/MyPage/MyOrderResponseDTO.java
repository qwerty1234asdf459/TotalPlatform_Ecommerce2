package com.example.demo.ecommerce.MyPage;

import java.util.List;

import com.example.demo.ecommerce.Entity.Payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyOrderResponseDTO {
	
	 private List<Payment> payments;
	 
	 private List<String> productNames;
	 
	 private List<Integer> totalPrices;

	public MyOrderResponseDTO(List<Payment> payments, List<String> productNames, List<Integer> totalPrices) {
		super();
		this.payments = payments;
		this.productNames = productNames;
		this.totalPrices = totalPrices;
	}
	
}
