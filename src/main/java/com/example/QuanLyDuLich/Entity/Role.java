package com.example.QuanLyDuLich.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    String rolename;
    String description;
    @ManyToMany
    Set<Permission> permissions;
}
