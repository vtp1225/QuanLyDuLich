package com.example.QuanLyDuLich.Repository;

import com.example.QuanLyDuLich.Entity.LoggoutToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggoutTokenRepository  extends JpaRepository<LoggoutToken,String> {
}
