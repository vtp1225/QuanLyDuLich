package com.example.QuanLyDuLich.dto.Request;
import com.example.QuanLyDuLich.Validatior.DobConstraint;
import com.example.QuanLyDuLich.Validatior.PassConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    String tendangnhap;
    String sodienthoai;
    String email;
    String diachi;
    @DobConstraint(min=21,message = "DATE_VALID")
    LocalDateTime ngaysinh;
    @PassConstraint(message = "PASS_VALID")
    String password;
    boolean trangthai;
}

