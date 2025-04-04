package com.example.QuanLyDuLich.Mapper;

import com.example.QuanLyDuLich.Entity.Role;
import com.example.QuanLyDuLich.dto.Request.RoleRequest;
import com.example.QuanLyDuLich.dto.Respone.RoleRespone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole (RoleRequest request);

    RoleRespone toRoleRespone(Role role);
}
