package com.example.QuanLyDuLich.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String tendangnhap;
    String sodienthoai;
    String email;
    String diachi;
    LocalDateTime ngaysinh;
    @Column(updatable = false)
    LocalDateTime ngaydk = LocalDateTime.now();
    @Size(min = 8)
    String password;
    boolean trangthai;
    @ManyToMany
    Set<Role> roles;
    @PrePersist
    public void prePersist() {
        if (ngaydk == null) {
            ngaydk = LocalDateTime.now();
        }
    }
}
