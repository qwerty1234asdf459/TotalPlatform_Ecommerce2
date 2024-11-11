package com.example.demo.ecommerce.Payment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Coupon;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.User;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentService {
	
	private final PaymentRepository pr;
	
	
	public Payment createPayment(User user, Coupon coupon,String address, String delRequest,String OrderId) {
		Payment p = new Payment();
		p.setUser(user);
		p.setCoupon(coupon);
		p.setCreateDate(LocalDateTime.now());
		p.setDeliveryno(" ");
		p.setAddress(address);
		p.setName(user.getName());
		p.setTell(user.getTell());
		p.setPaymentState("결제 완료");
		p.setDeliveryno("11111");
		p.setOrderNo(OrderId);
		p.setDeliveryState("상품 준비중");

		this.pr.save(p);
		
		return p;
	}
	
//	public String createOrderNo() {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//		String currentTime = dateFormat.format(new Date());
//		String randomAlpa = RandomStringUtils.randomAlphabetic(4);
//		
//		return randomAlpa+currentTime;
//	}
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

//	public List<Payment> getPayment(Integer userId, Integer period) throws CanNotFoundException {
//		List<Payment> p = this.pr.findByUserCreate(userId, period);
//		if(!p.isEmpty()) {
//			return p;
//		}
//		else {
//			throw new CanNotFoundException("데이터를 찾을 수 없습니다.");
//		}
//	}
	
	public List<Payment> getPaymentPeriod(Integer userId, Integer period) throws CanNotFoundException {
	      List<Payment> p = this.pr.findByUserCreate(userId, period);
	      if(!p.isEmpty()) {
	         return p;
	      }
	      else {
	         throw new CanNotFoundException("해당 기간의 결제내역이 없습니다.");
	      }
	   }
	
}


