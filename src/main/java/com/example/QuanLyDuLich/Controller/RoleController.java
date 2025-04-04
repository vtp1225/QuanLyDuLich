package com.example.QuanLyDuLich.Controller;

import com.example.QuanLyDuLich.Service.RoleService;
import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import com.example.QuanLyDuLich.dto.Request.RoleRequest;
import com.example.QuanLyDuLich.dto.Respone.RoleRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleController {
    RoleService roleService;
    @PostMapping
    ApiRespone create (@RequestBody RoleRequest request)
    {
        return ApiRespone.<RoleRespone>builder()
                .result(roleService.createrole(request))
                .code(100)
                .message("thanh cong")
                .build();
    }
    @GetMapping
    ApiRespone getAll()
    {
        return ApiRespone.<List<RoleRespone>>builder()
                .code(1000)
                .message("Lay thong tin role")
                .result(roleService.getAllRole())
                .build();
    }
    @DeleteMapping("{rolename}")
    ApiRespone delete(@PathVariable("rolename") String name)
    {
        roleService.deleteRole(name);
        return ApiRespone.builder()
                .code(1000)
                .message("Da xoa thanh cong")
                .build();
    }
}
