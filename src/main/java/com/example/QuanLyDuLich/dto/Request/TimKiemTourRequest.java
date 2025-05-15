package com.example.QuanLyDuLich.dto.Request;

import com.example.QuanLyDuLich.enums.TheLoai;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimKiemTourRequest {
    String tentour;
    String tinhthanh;
    @Enumerated(EnumType.STRING)
    TheLoai theloai;
    BigDecimal giaban;
    String khuvuc;
    int ngay;

}
