package com.example.QuanLyDuLich.dto.Respone;

import com.example.QuanLyDuLich.enums.TheLoai;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class KhuyenMaiRespone {
    String idkhuyenmai;
    String tenkhuyenmai;
    float phantramkm;
    LocalDate thoihan;
}
