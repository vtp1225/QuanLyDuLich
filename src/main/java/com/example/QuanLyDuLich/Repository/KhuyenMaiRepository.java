package com.example.QuanLyDuLich.Repository;

import com.example.QuanLyDuLich.Entity.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai,String> {
    void deleteByThoihanBefore(LocalDate date);
}
