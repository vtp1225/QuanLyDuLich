package com.example.QuanLyDuLich.Controller;

import com.example.QuanLyDuLich.Service.DiemDungService;
import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import com.example.QuanLyDuLich.dto.Request.DiemDungRequest;
import com.example.QuanLyDuLich.dto.Request.ListDiemDungRequest;
import com.example.QuanLyDuLich.dto.Respone.DiemDungRespone;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diemdung")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DiemDungController {
    DiemDungService diemDungService;
    @PostMapping
    ApiRespone<DiemDungRespone> createDiemDung(@RequestBody @Valid DiemDungRequest request)
    {
        DiemDungRespone result= diemDungService.taoDiemDung(request);
        return ApiRespone.<DiemDungRespone>builder()
                .result(result)
                .message("Thanh cong")
                .build();
    }
    @PostMapping("/more")
    ApiRespone<List<DiemDungRespone>> createNhieuDiemDung(@RequestBody @Valid ListDiemDungRequest request)
    {
        var result= diemDungService.taoNhieuDiemDung(request);
        return ApiRespone.<List<DiemDungRespone>>builder()
                .result(result)
                .message("Thanh cong")
                .build();
    }
    @GetMapping("{id}")
    ApiRespone<DiemDungRespone> getDiemDung(@PathVariable("id") String id)
    {
        return ApiRespone.<DiemDungRespone>builder()
                .result(diemDungService.getDiemDung(id))
                .message("thanh cong")
                .build();
    }
    @GetMapping
    ApiRespone getAllDiemDung()
    {
        return ApiRespone.<List<DiemDungRespone>>builder()
                .result(diemDungService.getAllDiemDung())
                .message("Thanh cong")
                .build();
    }
    @DeleteMapping("{id}")
    ApiRespone deleteDiemDung(@PathVariable("id") String id)
    {
        diemDungService.deleteDiemDung(id);
        return ApiRespone.builder()
                .message("da xoa thanh cong")
                .build();
    }
}
