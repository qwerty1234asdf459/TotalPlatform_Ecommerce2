package com.example.demo.ecommerce.Authuser;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;



@Service
@Slf4j
public class AuthuserService {

    
    public Mono<ResponseEntity<String>> getNameWithHeader(String jwtToken){
    	
    	WebClient webClient = WebClient.create("http://192.168.17.254:8080");
    	
    	
    	Mono<ResponseEntity<String>> response = webClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/test")
                        .build())
                .header("Authorization", "Bearer "+jwtToken)
                .retrieve()
                .toEntity(String.class);

    	return response;

    	}
    	
	}
