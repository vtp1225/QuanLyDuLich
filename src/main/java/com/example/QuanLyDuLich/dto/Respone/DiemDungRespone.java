package com.example.QuanLyDuLich.dto.Respone;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DiemDungRespone {
    String iddiemdung;
    String tendiemdung;
    LocalDateTime thoigiandi;
    LocalDateTime thoigianroi;
    List<String> anhminhhoa;
}
