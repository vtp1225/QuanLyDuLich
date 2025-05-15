package com.example.QuanLyDuLich.Mapper;

import com.example.QuanLyDuLich.Entity.DiemDung;
import com.example.QuanLyDuLich.Entity.LichTrinh;
import com.example.QuanLyDuLich.dto.Request.CreateLichTrinhRequest;
import com.example.QuanLyDuLich.dto.Request.DiemDungRequest;
import com.example.QuanLyDuLich.dto.Request.UpdateLichTrinhRequest;
import com.example.QuanLyDuLich.dto.Respone.DiemDungRespone;
import com.example.QuanLyDuLich.dto.Respone.LichTrinhRespone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.LinkedHashMap;

@Mapper(componentModel = "spring")
public interface LichTrinhMapper {
    @Mapping(target = "diemdungs",ignore = true)
    LichTrinh toLichTrinh(CreateLichTrinhRequest request);
    LichTrinhRespone toLichTrinhRespone(LichTrinh lichTrinh);
    void UpdateLichTrinh(@MappingTarget LichTrinh lichTrinh, UpdateLichTrinhRequest request);
}
