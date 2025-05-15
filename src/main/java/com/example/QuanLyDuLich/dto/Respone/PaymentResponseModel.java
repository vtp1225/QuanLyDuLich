package com.example.QuanLyDuLich.dto.Respone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PaymentResponseModel {
    @Id
    @Column(nullable = false)
    private String id;

    private String orderDescription;
    private String transactionId;
    private String orderId;
    private String paymentMethod;
    private String paymentId;
    private boolean success;
    private String token;
    private String vnPayResponseCode;

    private LocalDateTime transactionTime;
}