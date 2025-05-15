package com.example.QuanLyDuLich.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LichTrinh {
    @Id
    String idlichtrinh;
    LocalDateTime thoigiandi;
    LocalDateTime thoigianve;
    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "lichtrinhid")
    Set<DiemDung> diemdungs;
}
