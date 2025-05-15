package com.example.QuanLyDuLich.dto.Respone;

import com.example.QuanLyDuLich.Entity.KhuyenMai;
import com.example.QuanLyDuLich.Entity.LichTrinh;
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

public class TourRespone {
    String id;
    String tentour;
    String tinhthanh;
    String khuvuc;
    String anhminhhoa;
    TheLoai theloai;
    @Column(precision = 10,scale = 2)
    BigDecimal giagoc;
    String gioithieu;
    LichTrinhRespone thongtinlichtrinh;
    Set<KhuyenMaiRespone> cackhuyenmai;
    BigDecimal giaban;
}
