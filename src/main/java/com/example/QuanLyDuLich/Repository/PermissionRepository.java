package com.example.QuanLyDuLich.Repository;

import com.example.QuanLyDuLich.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {
}
