package com.example.QuanLyDuLich.Controller;

import com.example.QuanLyDuLich.Service.PermissionService;
import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import com.example.QuanLyDuLich.dto.Request.PermissionRequest;
import com.example.QuanLyDuLich.dto.Respone.PermissionRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PermissionController {
    PermissionService permissionService;
    @PostMapping
    ApiRespone createPermisson(@RequestBody PermissionRequest request)
    {
        var result = permissionService.create(request);
        return ApiRespone.<PermissionRespone>builder()
                .message("Thanh cong")
                .code(1000)
                .result(result)
                .build();
    }
    @GetMapping
    ApiRespone getAll()
    {
        var result = permissionService.getAll();
        return ApiRespone.<List<PermissionRespone>>builder()
                .result(result)
                .build();
    }
    @DeleteMapping("/{permission}")
    ApiRespone delete(@PathVariable String permission)
    {
        permissionService.delete(permission);
        return ApiRespone.builder()
                .code(1000)
                .message("Da xoa thanh cong")
                .build();
    }
}
