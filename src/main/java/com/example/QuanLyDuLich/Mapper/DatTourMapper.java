package com.example.QuanLyDuLich.Mapper;

import com.example.QuanLyDuLich.Entity.DatTour;
import com.example.QuanLyDuLich.dto.Request.DatTourRequest;
import com.example.QuanLyDuLich.dto.Respone.DatTourRespone;
import org.mapstruct.Mapper;

import java.util.Map;
@Mapper(componentModel = "spring")
public interface DatTourMapper {
    DatTourRespone toDatTourRespone(DatTour datTour);
    DatTour toDatTour(DatTourRequest datTourRequest);
}
