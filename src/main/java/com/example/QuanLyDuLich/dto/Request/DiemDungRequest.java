package com.example.QuanLyDuLich.dto.Request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DiemDungRequest {
    String iddiemdung;
    String tendiemdung;
    LocalDateTime thoigiandi;
    LocalDateTime thoigianroi;
    List<String> anhminhhoa;
}
