package com.example.QuanLyDuLich.Service;

import com.example.QuanLyDuLich.Entity.Role;
import com.example.QuanLyDuLich.Exception.AppExceptions;
import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.Mapper.RoleMapper;
import com.example.QuanLyDuLich.Repository.PermissionRepository;
import com.example.QuanLyDuLich.Repository.RoleRepository;
import com.example.QuanLyDuLich.dto.Request.RoleRequest;
import com.example.QuanLyDuLich.dto.Respone.RoleRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleService {
    RoleRepository repository;
    PermissionRepository permissionRepository;
    RoleMapper mapper;
    @PreAuthorize("hasRole('ADMIN')")
    public RoleRespone createrole(RoleRequest request)
    {
        Role role =new Role();
        role =mapper.toRole(request);
        var permissions= permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role= repository.save(role);
        return mapper.toRoleRespone(role);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleRespone> getAllRole()
    {
        return repository.findAll()
                .stream().map(mapper::toRoleRespone).toList();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(String rolename)
    {
        Role role= repository.findById(rolename).orElseThrow(()->new AppExceptions(ErrorCode.NOT_EXIST_ROLE));
        repository.delete(role);
    }
}
