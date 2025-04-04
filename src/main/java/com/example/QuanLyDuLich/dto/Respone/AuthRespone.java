package com.example.QuanLyDuLich.dto.Respone;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthRespone {
    String token;
    boolean authresult;

}
