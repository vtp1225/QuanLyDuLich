package com.example.QuanLyDuLich.dto.Request;


import com.example.QuanLyDuLich.enums.TheLoai;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTourRequest {
    @Id
    String id;
    String tentour;
    String tinhthanh;
    String khuvuc;
    String anhminhhoa;
    @Enumerated(EnumType.STRING)
    TheLoai theloai;
    BigDecimal giagoc;
    String gioithieu;
    String malichtrinh;
    Set<String> makhuyenmais;
}
