package com.example.demo.ecommerce.Payment;

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
    public String confirmPayment(String paymentKey, String orderId, Long amount) throws IOException {
        String url = "https://api.tosspayments.com/v1/payments/" + paymentKey;
        RequestBody body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            gson.toJson(new PaymentConfirmRequest(paymentKey, orderId, amount))
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
