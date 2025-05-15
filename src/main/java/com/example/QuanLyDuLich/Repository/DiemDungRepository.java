package com.example.QuanLyDuLich.Repository;

import com.example.QuanLyDuLich.Entity.DiemDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiemDungRepository extends JpaRepository<DiemDung,String> {
}
