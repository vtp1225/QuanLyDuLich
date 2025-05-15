package com.example.QuanLyDuLich.dto.Request;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateLichTrinhRequest {
    @Id
    String idlichtrinh;
    LocalDateTime thoigiandi;
    LocalDateTime thoigianve;
    Set<String> diemdungs;
}
