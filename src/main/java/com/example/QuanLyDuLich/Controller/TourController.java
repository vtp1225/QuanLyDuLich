package com.example.QuanLyDuLich.Controller;

import com.example.QuanLyDuLich.Service.DiemDungService;
import com.example.QuanLyDuLich.Service.LichTrinhService;
import com.example.QuanLyDuLich.Service.TourService;
import com.example.QuanLyDuLich.dto.Request.*;
import com.example.QuanLyDuLich.dto.Respone.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/tours")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TourController {
    TourService tourService;
    LichTrinhService lichTrinhService;
    DiemDungService diemDungService;
    @PostMapping
    ApiRespone<TourRespone> createTour(@RequestBody CreateTourRequest request)
    {
        TourRespone result = tourService.createTour(request);
        return ApiRespone.<TourRespone>builder()
                .result(result)
                .build();
    }
    @GetMapping
    ApiRespone getAllTours()
    {
        return  ApiRespone.<List<TourRespone>>builder()
                .result(tourService.getAllTour())
                .message("Thanh cong")
                .build();
    }
    @GetMapping("{id}")
    ApiRespone getTour(@PathVariable("id") String id)
    {
        return ApiRespone.<TourRespone>builder()
                .result(tourService.getOneTour(id))
                .message("Thanh cong")
                .build();
    }
    @GetMapping("/timkiem")
    ApiRespone getTourTheoTiemKiem(@RequestBody TimKiemTourRequest request)
    {
        return ApiRespone.<List<TourRespone>>builder()
                .result(tourService.getFindingTour(request))
                .message("Thanh cong")
                .build();
    }
    @PutMapping("{id}")
    ApiRespone updateTour(@RequestBody UpdateTourRequest request)
    {
        return ApiRespone.<TourRespone>builder()
                .result(tourService.updateTour(request))
                .message("Thanh cong")
                .build();
    }
    @DeleteMapping("{id}")
    ApiRespone deleteTour(@PathVariable ("id") String id)
    {
        tourService.DeleteTour(id);
    return ApiRespone.builder()
            .message("xoa thanh cong")
            .build();
    }
}
