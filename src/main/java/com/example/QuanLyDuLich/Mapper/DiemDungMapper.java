package com.example.QuanLyDuLich.Mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.QuanLyDuLich.Entity.DiemDung;
import com.example.QuanLyDuLich.dto.Request.DiemDungRequest;
import com.example.QuanLyDuLich.dto.Respone.DiemDungRespone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiemDungMapper {
    DiemDung toDiemDung(DiemDungRequest request);
    DiemDungRespone toDiemDungRespone(DiemDung diemDung);
}
