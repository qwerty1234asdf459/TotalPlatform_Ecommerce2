package com.example.demo.ecommerce.Payment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentService {
	
	private final PaymentRepository pr;
	
	public List<Payment> getPayment(Integer id) throws CanNotFoundException {
		List<Payment> p = this.pr.findByUserId(id);
		if(!p.isEmpty()) {
			return p;
		}
		else {
			throw new CanNotFoundException("데이터를 찾을 수 없습니다.");
		}
	}

}
