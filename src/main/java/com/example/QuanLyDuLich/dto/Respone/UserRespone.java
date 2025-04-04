package com.example.QuanLyDuLich.dto.Respone;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRespone {
    String id;
    String tendangnhap;
    String sodienthoai;
    @Email(message = "EMAIL_SIZE")
    String email;
    String diachi;
    LocalDateTime ngaydk;
    LocalDateTime ngaysinh;
    @Size(min=8, message = "SIZE_PASSWORD")
    String password;
    boolean trangthai;
    Set<RoleRespone> roles;
}
