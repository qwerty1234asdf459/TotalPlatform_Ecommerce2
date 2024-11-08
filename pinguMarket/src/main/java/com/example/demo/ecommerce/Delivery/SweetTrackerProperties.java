package com.example.demo.ecommerce.Delivery;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("sweet-tracker")
@Data
public class SweetTrackerProperties {
	
	private String apiKey;

}
