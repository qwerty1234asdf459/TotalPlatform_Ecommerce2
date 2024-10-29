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
		List<Payment> p = this.pr.findByUser_UserId(id);
		if(!p.isEmpty()) {
			return p;
		}
		else {
			throw new CanNotFoundException("데이터를 찾을 수 없습니다.");
		}
	}
	
	public Payment getPayment1(Integer id) throws CanNotFoundException {
		Optional<Payment> p = this.pr.findById(id);
		if(p.isPresent()) {
			return p.get();
		}else {
			throw new CanNotFoundException("데이터를 찾을 수 없습니다.");
		}
	}
	
	public String getFirstProductName(Payment payment) {
        return payment.getPaymentDetailList().stream() // Payment의 PaymentDetail 리스트를 스트림으로 변환
                .findFirst() // 첫 번째 상품을 가져옴
                .map(detail -> detail.getProduct().getName()) // 그 첫 번째 상품의 name을 가져옴
                .orElse("상품이 없습니다."); // 없을 때 나오는 것
    }
	
	public Integer getTotalPrice(Payment payment) {
		return payment.getPaymentDetailList().stream()
				.mapToInt(detail -> detail.getPrice() * detail.getCount())
				.sum();
	}
	
}


