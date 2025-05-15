package com.example.QuanLyDuLich.dto.Request;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PaymentInformationModel {
    @Id
    private String id;

    private String orderType;
    private double amount;
    private String orderDescription;
    private String name;
}
