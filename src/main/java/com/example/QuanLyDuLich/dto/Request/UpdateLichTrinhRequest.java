package com.example.QuanLyDuLich.dto.Request;

import java.time.LocalDateTime;
import java.util.Set;

public class UpdateLichTrinhRequest {
    LocalDateTime thoigiandi;
    LocalDateTime thoigianve;
    Set<String> diemdungs;
}
