package com.example.QuanLyDuLich.Repository;

import com.example.QuanLyDuLich.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
}
