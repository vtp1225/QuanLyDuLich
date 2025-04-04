package com.example.QuanLyDuLich.Mapper;

import com.example.QuanLyDuLich.Entity.Permission;
import com.example.QuanLyDuLich.dto.Request.PermissionRequest;
import com.example.QuanLyDuLich.dto.Respone.PermissionRespone;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface PermissionMapper {
    Permission ToPermission(PermissionRequest request);
    PermissionRespone ToPermissionRespone(Permission permission);
}
