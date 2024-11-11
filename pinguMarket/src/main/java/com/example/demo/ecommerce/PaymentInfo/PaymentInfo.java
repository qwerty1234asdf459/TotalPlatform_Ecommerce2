package com.example.demo.ecommerce.PaymentInfo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.demo.ecommerce.Entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_info")
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "payment_key")
    private String paymentKey;
    
    @Column(name = "order_id")
    private String orderId;
    
    private Long amount;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    
    @Column(name = "payment_method") 
    private String paymentMethod;
    
    @Column(name = "card_info") 
    private String cardInfo;
    
    @CreationTimestamp
    @Column(name = "created_at") 
    private LocalDateTime createdAt;
    
}