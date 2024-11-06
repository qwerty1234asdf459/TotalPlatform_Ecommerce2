package com.example.demo.ecommerce.Payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PaymentPageController {
    @GetMapping("/payment2")
    public String paymentPage() {
        return "payment";
    }
    @GetMapping("/success")
    public String successPage() {
    	System.out.println("너냐?");
        return "success";
    }
    @GetMapping("/fail")
    public String failPage() {
        return "fail";
    }
}