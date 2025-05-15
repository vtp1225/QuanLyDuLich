package com.example.QuanLyDuLich.dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListDiemDungRequest {
    List<DiemDungRequest> diemDungList;
}
