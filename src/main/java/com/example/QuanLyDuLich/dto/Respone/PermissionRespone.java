package com.example.QuanLyDuLich.dto.Respone;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)


public class PermissionRespone {
    @Id
    String name;
    String description;
}
