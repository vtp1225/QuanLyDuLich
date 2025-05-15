package com.example.QuanLyDuLich.Service;


import com.example.QuanLyDuLich.Entity.DiemDung;
import com.example.QuanLyDuLich.Entity.LichTrinh;
import com.example.QuanLyDuLich.Exception.AppExceptions;
import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.Mapper.DiemDungMapper;
import com.example.QuanLyDuLich.Repository.DiemDungRepository;
import com.example.QuanLyDuLich.Repository.LichTrinhRepository;
import com.example.QuanLyDuLich.dto.Request.DiemDungRequest;
import com.example.QuanLyDuLich.dto.Request.ListDiemDungRequest;
import com.example.QuanLyDuLich.dto.Respone.DiemDungRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DiemDungService {
    DiemDungRepository repository;
    DiemDungMapper diemDungMapper;
    @PreAuthorize("hasRole('ADMIN')")
    public DiemDungRespone taoDiemDung(DiemDungRequest request)
    {
        DiemDung diemDung=diemDungMapper.toDiemDung(request);
        return  diemDungMapper.toDiemDungRespone(repository.save(diemDung));

    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<DiemDungRespone> taoNhieuDiemDung(ListDiemDungRequest request)
    {
        List<DiemDungRespone> result = new ArrayList<>();
        for(DiemDungRequest d: request.getDiemDungList()) {
            DiemDung diemDung= diemDungMapper.toDiemDung(d);
             diemDungMapper.toDiemDungRespone(repository.save(diemDung));
             result.add(diemDungMapper.toDiemDungRespone(repository.save(diemDung)));
        }
        return  result;
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDiemDung(String id)
    {
        DiemDung diemDung=repository.findById(id)
                .orElseThrow(()->new RuntimeException());
        repository.delete(diemDung);
    }
    public DiemDungRespone getDiemDung(String id)
    {
        DiemDung diemDung = repository.findById(id)
                .orElseThrow(()->new RuntimeException());
        return diemDungMapper.toDiemDungRespone(diemDung);
    }
    public List<DiemDungRespone> getAllDiemDung()
    {
        List<DiemDung> diemDungs = repository.findAll();
        return diemDungs.stream().map(diemDungMapper::toDiemDungRespone).toList();
    }
}
