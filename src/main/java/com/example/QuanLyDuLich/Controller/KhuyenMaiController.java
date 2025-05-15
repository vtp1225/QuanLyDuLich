package com.example.QuanLyDuLich.Controller;


import com.example.QuanLyDuLich.Service.KhuyenMaiService;
import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import com.example.QuanLyDuLich.dto.Request.KhuyenMaiRequest;
import com.example.QuanLyDuLich.dto.Respone.KhuyenMaiRespone;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/khuyenmai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class KhuyenMaiController {
    KhuyenMaiService khuyenMaiService;

    @PostMapping
    ApiRespone<KhuyenMaiRespone> taoKhuyenMai(@RequestBody @Valid KhuyenMaiRequest request)
    {
        KhuyenMaiRespone result= khuyenMaiService.createKhuyenMai(request);
        return ApiRespone.<KhuyenMaiRespone>builder()
                .result(result)
                .message("Thanh cong")
                .build();
    }
    @GetMapping("{id}")
    ApiRespone<KhuyenMaiRespone> getKhuyenMai(@PathVariable("id") String id)
    {
        return ApiRespone.<KhuyenMaiRespone>builder()
                .result(khuyenMaiService.getKhuyenMai(id))
                .message("thanh cong")
                .build();
    }
    @DeleteMapping("{id}")
    ApiRespone deletaKhuyenMai(@PathVariable("id") String id)
    {
        khuyenMaiService.deleteKhuyenMai(id);
        return ApiRespone.builder()
                .message("da xoa thanh cong")
                .build();
    }
}
