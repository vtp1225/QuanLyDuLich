package com.example.QuanLyDuLich.Service;


import com.example.QuanLyDuLich.Entity.KhuyenMai;
import com.example.QuanLyDuLich.Exception.AppExceptions;
import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.Mapper.KhuyenMaiMapper;
import com.example.QuanLyDuLich.Repository.KhuyenMaiRepository;
import com.example.QuanLyDuLich.dto.Request.KhuyenMaiRequest;
import com.example.QuanLyDuLich.dto.Respone.KhuyenMaiRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class KhuyenMaiService {
    KhuyenMaiRepository khuyenMaiRepository;
    KhuyenMaiMapper mapper;
    @PreAuthorize("hasRole('ADMIN')")
    public KhuyenMaiRespone createKhuyenMai(KhuyenMaiRequest request)
    {
        KhuyenMai km= mapper.toKhuyenMai(request);
        return mapper.toKhuyenMaiRespone(khuyenMaiRepository.save(km));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteKhuyenMai(String id)
    {
        KhuyenMai km= khuyenMaiRepository.findById(id)
                .orElseThrow(()-> new AppExceptions(ErrorCode.NOT_EXIST));
        khuyenMaiRepository.delete(km);
    }
    public KhuyenMaiRespone getKhuyenMai(String id)
    {
        KhuyenMai km= khuyenMaiRepository.findById(id)
                .orElseThrow(()->new RuntimeException());
        return mapper.toKhuyenMaiRespone(km);
    }
    public List<KhuyenMaiRespone> getAllKhuyenMai()
    {
        List<KhuyenMai> khuyenMais = khuyenMaiRepository.findAll();
        return khuyenMais.stream().map(mapper::toKhuyenMaiRespone).toList();
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void autoxoaKM()
    {
        khuyenMaiRepository.deleteByThoihanBefore(LocalDate.now());
    }
}

