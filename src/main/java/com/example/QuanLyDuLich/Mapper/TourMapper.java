package com.example.QuanLyDuLich.Mapper;

import com.example.QuanLyDuLich.Entity.Role;
import com.example.QuanLyDuLich.Entity.Tour;
import com.example.QuanLyDuLich.Entity.User;
import com.example.QuanLyDuLich.dto.Request.CreateTourRequest;
import com.example.QuanLyDuLich.dto.Request.RoleRequest;
import com.example.QuanLyDuLich.dto.Request.UpdateTourRequest;
import com.example.QuanLyDuLich.dto.Request.UpdateUserRequest;
import com.example.QuanLyDuLich.dto.Respone.RoleRespone;
import com.example.QuanLyDuLich.dto.Respone.TourRespone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourMapper {
    @Mapping(target = "giaban", ignore = true)
    @Mapping(target = "cackhuyenmai",ignore = true)
    @Mapping(target = "thongtinlichtrinh",ignore = true)
    Tour toTour (CreateTourRequest request);
    TourRespone toTourRespone(Tour tour);
    @Mapping(target = "cackhuyenmai",ignore = true)
    @Mapping(target = "thongtinlichtrinh",ignore = true)
    void updateTour(@MappingTarget Tour tour, UpdateTourRequest request);

}
