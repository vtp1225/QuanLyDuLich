package com.example.QuanLyDuLich.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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
    private BigDecimal amount;
    private String orderDescription;
    private String name;
}
