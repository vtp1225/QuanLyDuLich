package com.example.QuanLyDuLich.dto.Respone;

import com.example.QuanLyDuLich.Entity.DiemDung;
import com.example.QuanLyDuLich.Entity.Tour;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class LichTrinhRespone {
    String idlichtrinh;
    LocalDateTime thoigiandi;
    LocalDateTime thoigianve;
    Set<DiemDungRespone> diemdungs;
}
