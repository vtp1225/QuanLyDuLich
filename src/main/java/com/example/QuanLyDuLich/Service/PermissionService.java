package com.example.QuanLyDuLich.Service;

import com.example.QuanLyDuLich.Entity.Permission;
import com.example.QuanLyDuLich.Exception.AppExceptions;
import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.Mapper.PermissionMapper;
import com.example.QuanLyDuLich.Repository.PermissionRepository;
import com.example.QuanLyDuLich.dto.Request.PermissionRequest;
import com.example.QuanLyDuLich.dto.Respone.PermissionRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionService {
    PermissionRepository repository;
    PermissionMapper mapper;
    @PreAuthorize("hasRole('ADMIN')")
    public PermissionRespone create(PermissionRequest request)
    {
        Permission permission= new Permission();
        permission= mapper.ToPermission(request);
        return mapper.ToPermissionRespone(repository.save(permission));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionRespone> getAll()
    {
        var permissions = repository.findAll();
        return permissions.stream().map(mapper::ToPermissionRespone).toList();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String permission)
    {
        Permission permission1 = repository.findById(permission)
                .orElseThrow(()->new AppExceptions(ErrorCode.PERMISSION_NOTEXIST));
        repository.delete(permission1);
    }

}
