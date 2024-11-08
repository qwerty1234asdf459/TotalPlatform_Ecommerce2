package com.example.demo.ecommerce.PaymentInfo;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PaymentInfoController {
	
	private final PaymentInfoService paymentInfoService;
	
	@PostMapping("/payments/request")
	public String requestPayment(@RequestBody PaymentRequest request) throws IOException {
	    return paymentInfoService.requestPayment(request);
	}
	@PostMapping("/payments/confirm")
	public String confirmPayment(@RequestParam String paymentKey,
	                             @RequestParam String orderId,
	                             @RequestParam Long amount) throws IOException {
	    return paymentInfoService.confirmPayment(paymentKey, orderId, amount);
	}
	@GetMapping("/payments/{paymentKey}")
	public String getPayment(@PathVariable String paymentKey) throws IOException {
	    return paymentInfoService.getPayment(paymentKey);
	}
	
}
