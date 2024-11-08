package com.example.demo.ecommerce.Delivery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController //리턴을 무조건 json 키밸류 형태로 응답받을 수 있게
public class DeliveryController {
	
    private final DeliveryService deliveryService;
    
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
    
    @PostMapping("/api/delivery/status")
    public String getDeliveryStatus(@RequestParam String carrierCode, @RequestParam String invoiceNumber) {
        return deliveryService.getDeliveryStatus(carrierCode, invoiceNumber);
    }
}