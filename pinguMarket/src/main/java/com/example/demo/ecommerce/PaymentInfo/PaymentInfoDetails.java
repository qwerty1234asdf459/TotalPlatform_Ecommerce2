package com.example.demo.ecommerce.PaymentInfo;

import com.google.gson.Gson;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentInfoDetails {
    // 결제 기본 정보
    private String paymentKey;      // 토스페이먼츠 고유 결제 키
    private String orderId;         // 주문 고유 번호
    private Long totalAmount;            // 결제 금액
    private String currency;        // 통화 (기본 KRW)
    // 결제 방법 정보
    private String method;   // 결제 수단 (카드, 계좌이체 등)
    private String status;          // 결제 상태 (DONE, CANCELED 등)
    // 카드 정보 (카드 결제 시)
    private CardInfo card;
    private EasyPay easyPay;
    
    @Getter
    @Setter
    public static class CardInfo {
    	private Long amount;
        private String issuerCode;      // 카드사 코드
        private String cardType;        // 카드 타입 (신용/체크)
        private String bin;             // 카드 번호 앞 6자리
        private String installmentPlan; // 할부 정보
    }
    
    @Getter
    @Setter
    public static class EasyPay {
        private Long amount;   // easyPay 안의 amount 필드
        
    }
    
    // JSON 파싱 메서드
    public static PaymentInfoDetails fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, PaymentInfoDetails.class);
    }
}