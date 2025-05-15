package com.example.QuanLyDuLich.Service;


import com.example.QuanLyDuLich.Entity.DiemDung;
import com.example.QuanLyDuLich.Entity.LichTrinh;
import com.example.QuanLyDuLich.Entity.Tour;
import com.example.QuanLyDuLich.Exception.AppExceptions;
import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.Mapper.LichTrinhMapper;
import com.example.QuanLyDuLich.Repository.DiemDungRepository;
import com.example.QuanLyDuLich.Repository.LichTrinhRepository;
import com.example.QuanLyDuLich.Repository.TourRepository;
import com.example.QuanLyDuLich.dto.Request.CreateLichTrinhRequest;
import com.example.QuanLyDuLich.dto.Request.UpdateLichTrinhRequest;
import com.example.QuanLyDuLich.dto.Respone.LichTrinhRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LichTrinhService {
    LichTrinhRepository repository;
    DiemDungRepository diemDungRepository;
    LichTrinhMapper mapper;
    @PreAuthorize("hasRole('ADMIN')")
    public LichTrinhRespone createLichTrinh(CreateLichTrinhRequest request)
    {
        LichTrinh lichTrinh= mapper.toLichTrinh(request);
        var diemDungs =  diemDungRepository.findAllById(request.getDiemdungs());
        lichTrinh.setDiemdungs(new HashSet<>(diemDungs));
        repository.save(lichTrinh);
        LichTrinhRespone respone=mapper.toLichTrinhRespone(lichTrinh);
        return respone;
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLichTrinh(String id)
    {
        LichTrinh lichTrinh = repository.findById(id)
                .orElseThrow(()-> new AppExceptions(ErrorCode.LICHTRINH_NOTEXIST));
        repository.delete(lichTrinh);
    }
    public LichTrinhRespone getLichTrinh(String id) {
        LichTrinh lichTrinh = repository.findById(id)
                .orElseThrow(() -> new AppExceptions(ErrorCode.LICHTRINH_NOTEXIST));
        LichTrinhRespone respone = mapper.toLichTrinhRespone(lichTrinh);
        return respone;
    }
    public List<LichTrinhRespone> getAllLich()
    {
        List<LichTrinh> lichTrinhs = repository.findAll();
        return lichTrinhs.stream().map(mapper::toLichTrinhRespone).toList();
    }
    public LichTrinhRespone updateLichTrinh(UpdateLichTrinhRequest request, String id)
    {
        LichTrinh lichTrinh = repository.findById(id).orElseThrow(
                ()->new AppExceptions(ErrorCode.LICHTRINH_NOTEXIST)
        );
        mapper.UpdateLichTrinh(lichTrinh,request);
        return mapper.toLichTrinhRespone(repository.save(lichTrinh));
    }
}
