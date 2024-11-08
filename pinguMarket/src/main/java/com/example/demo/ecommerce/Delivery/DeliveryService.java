package com.example.demo.ecommerce.Delivery;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class DeliveryService {
	
    private final RestTemplate restTemplate;
    private final SweetTrackerProperties sweetTrackerProperties;
    
    public DeliveryService(SweetTrackerProperties sweetTrackerProperties) {
        this.restTemplate = new RestTemplate();
        this.sweetTrackerProperties = sweetTrackerProperties;
    }
//    생성자
    
    public String getDeliveryStatus(String carrierCode, String invoiceNumber) {
        // API URL 구성
        String url = "http://info.sweettracker.co.kr/api/v1/trackingInfo" + // 기본 uri
                     "?t_key=" + sweetTrackerProperties.getApiKey() + // application.properties에 기입한 api key
                     "&t_code=" + carrierCode + // 택배회사 코드
                     "&t_invoice=" + invoiceNumber; // 운송장 번호
        // HTTP 헤더 설정 (필요한 경우)
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // GET 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class); // 위에서 생성한 url에 get 요청을 넣고 entity는 이 경우 헤더 정보를 담고 있고? 
        // 응답 처리
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();  // 성공적으로 데이터를 받으면 응답 본문 반환
        } else {
            throw new RuntimeException("배송 조회 실패: " + response.getStatusCode());
        }
    }
}