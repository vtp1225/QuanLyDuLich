package com.example.QuanLyDuLich.dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class KhuyenMaiRequest {
    String idkhuyenmai;
    String tenkhuyenmai;
    LocalDate thoihan;
    float phamtramkm;
}
