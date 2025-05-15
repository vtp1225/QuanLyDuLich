package com.example.QuanLyDuLich.Repository;

import com.example.QuanLyDuLich.Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
@Repository
public interface TourRepository extends JpaRepository<Tour,String> {

    void deleteByThongtinlichtrinh_ThoigiandiBefore(LocalDate date);
}
