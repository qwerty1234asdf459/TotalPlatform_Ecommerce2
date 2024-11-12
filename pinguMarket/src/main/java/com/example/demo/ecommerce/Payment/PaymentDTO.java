package com.example.demo.ecommerce.Payment;

import java.time.LocalDateTime;

import com.example.demo.ecommerce.Entity.Payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
	
	    private Integer paymentId;
	    
	    private LocalDateTime createDate;
	    
	    private String deliveryno;
	    
	    private String deliveryState;
	    // 필요한 필드들
	    public PaymentDTO(Payment payment) {
	        this.paymentId = payment.getPaymentId();
	        this.createDate = payment.getCreateDate();
	        this.deliveryno = payment.getDeliveryno();
	        this.deliveryState = payment.getDeliveryState();
	        // 필드 초기화
	    }

}
