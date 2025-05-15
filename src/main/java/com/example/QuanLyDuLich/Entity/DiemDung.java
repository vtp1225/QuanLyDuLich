package com.example.QuanLyDuLich.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class DiemDung {
    @Id
    String iddiemdung;
    String tendiemdung;
    LocalDateTime thoigiandi;
    LocalDateTime thoigianroi;
    List<String> anhminhhoa;
}
