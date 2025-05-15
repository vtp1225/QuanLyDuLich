package com.example.QuanLyDuLich.Mapper;

import com.example.QuanLyDuLich.Entity.KhuyenMai;
import com.example.QuanLyDuLich.dto.Request.KhuyenMaiRequest;
import com.example.QuanLyDuLich.dto.Respone.KhuyenMaiRespone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KhuyenMaiMapper {
    KhuyenMai toKhuyenMai(KhuyenMaiRequest request);
    KhuyenMaiRespone toKhuyenMaiRespone(KhuyenMai khuyenMai);

}
