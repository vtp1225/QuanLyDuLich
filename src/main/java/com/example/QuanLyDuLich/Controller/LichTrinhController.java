package com.example.QuanLyDuLich.Controller;

import com.example.QuanLyDuLich.Service.LichTrinhService;
import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import com.example.QuanLyDuLich.dto.Request.CreateLichTrinhRequest;
import com.example.QuanLyDuLich.dto.Request.UpdateLichTrinhRequest;
import com.example.QuanLyDuLich.dto.Respone.LichTrinhRespone;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lichtrinh")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LichTrinhController {

    LichTrinhService lichTrinhService;
    @PostMapping
    ApiRespone<LichTrinhRespone> createLichTrinh(@RequestBody @Valid CreateLichTrinhRequest request)
    {
        LichTrinhRespone result= lichTrinhService.createLichTrinh(request);
        return ApiRespone.<LichTrinhRespone>builder()
                .result(result)
                .message("Thanh cong")
                .build();
    }
    @GetMapping("{id}")
    ApiRespone<LichTrinhRespone> getLichTrinh(@PathVariable("id") String id)
    {
        return ApiRespone.<LichTrinhRespone>builder()
                .result(lichTrinhService.getLichTrinh(id))
                .message("thanh cong")
                .build();
    }
    @DeleteMapping("{id}")
    ApiRespone deleteLichTrinh(@PathVariable("id") String id)
    {
        lichTrinhService.deleteLichTrinh(id);
        return ApiRespone.builder()
                .message("Thanh cong")
                .build();
    }
    @PutMapping("{id}")
    ApiRespone updateLichTrinh(@PathVariable("id") String id, @RequestBody UpdateLichTrinhRequest request)
    {
        return ApiRespone.<LichTrinhRespone>builder()
                .result(lichTrinhService.updateLichTrinh(request,id))
                .message("Thanh Cong")
                .build();
    }

}
