package com.example.QuanLyDuLich.Mapper;

import com.example.QuanLyDuLich.Entity.User;
import com.example.QuanLyDuLich.dto.Request.CreateUserRequest;
import com.example.QuanLyDuLich.dto.Request.UpdateUserRequest;
import com.example.QuanLyDuLich.dto.Respone.UserRespone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
        @Mapping(target = "ngaydk",ignore = true)
        User toUser (CreateUserRequest request);
        UserRespone toUserRespone(User user);
        @Mapping(target = "roles",ignore = true)
    void updateUser(@MappingTarget User user, UpdateUserRequest request);
}
