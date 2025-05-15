package com.example.QuanLyDuLich.dto.Respone;

import com.example.QuanLyDuLich.Entity.Tour;
import com.example.QuanLyDuLich.Entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DatTourRespone {
    String madattour;
    User khachhang;
    Tour tour;
    LocalDate ngaydat;
    LocalDate thoihan;
}
