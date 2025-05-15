package com.example.QuanLyDuLich.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DatTour {
    @Id
    String madattour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "makhachhang", referencedColumnName = "id", nullable = false)
    User khachhang;
    @ManyToOne(fetch =FetchType.LAZY )
    @JoinColumn(name = "matour",referencedColumnName = "id",nullable = false)
    Tour tour;
    LocalDate ngaydat;
    LocalDate thoihan;
}
