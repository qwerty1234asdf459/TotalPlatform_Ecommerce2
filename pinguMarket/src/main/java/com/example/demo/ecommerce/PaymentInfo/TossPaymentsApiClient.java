package com.example.demo.ecommerce.PaymentInfo;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Base64;
@Component
public class TossPaymentsApiClient {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    @Value("${toss.client.api-key}")
    private String apiKey;
    @Value("${toss.client.secret-key}")
    private String secretKey;
    // 1. 결제 요청 메서드
    public String requestPayment(PaymentRequest request) throws IOException {
        String url = "https://api.tosspayments.com/v1/payments";
        RequestBody body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            gson.toJson(request)
        );
        Request httpRequest = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()))
            .post(body)
            .build();
        try (Response response = client.newCall(httpRequest).execute()) {
            return response.body().string();
        }
    }
    // 2. 결제 승인 메서드
    public String confirmPayment(String paymentKey, String orderId, Long amount) throws IOException {
        String url = "https://api.tosspayments.com/v1/payments/confirm";
        PaymentConfirmRequest confirmRequest = new PaymentConfirmRequest(paymentKey, orderId, amount);
        RequestBody body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            gson.toJson(confirmRequest)
        );
        Request httpRequest = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()))
            .post(body)
            .build();
        try (Response response = client.newCall(httpRequest).execute()) {
            return response.body().string();
        }
    }
    // 3. 결제 조회 메서드
    public String getPayment(String paymentKey) throws IOException {
        String url = "https://api.tosspayments.com/v1/payments/" + paymentKey;
        Request httpRequest = new Request.Builder()
            .url(url)
            .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes()))
            .get()
            .build();
        try (Response response = client.newCall(httpRequest).execute()) {
            return response.body().string();
        }
    }
}