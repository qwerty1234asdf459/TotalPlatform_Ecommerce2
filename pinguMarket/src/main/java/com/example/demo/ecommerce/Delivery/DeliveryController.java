package com.example.demo.ecommerce.Delivery;

import java.util.Map;

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
    public Map<String, Object> getDeliveryStatus(@RequestParam("code") String carrierCode, @RequestParam("invoice") String invoiceNumber) {
        return deliveryService.getDeliveryStatus(carrierCode, invoiceNumber);
        
    }
}