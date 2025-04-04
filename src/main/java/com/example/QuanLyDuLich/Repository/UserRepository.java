package com.example.QuanLyDuLich.Repository;

import com.example.QuanLyDuLich.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsBytendangnhap(String tendangnhap);
    Optional<User> findBytendangnhap(String tendangnhap);

}
