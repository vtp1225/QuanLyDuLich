package com.example.QuanLyDuLich.Service;


import com.example.QuanLyDuLich.Entity.LichTrinh;
import com.example.QuanLyDuLich.Entity.Tour;
import com.example.QuanLyDuLich.Exception.AppExceptions;
import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.Mapper.TourMapper;
import com.example.QuanLyDuLich.Repository.KhuyenMaiRepository;
import com.example.QuanLyDuLich.Repository.LichTrinhRepository;
import com.example.QuanLyDuLich.Repository.TourRepository;
import com.example.QuanLyDuLich.dto.Request.CreateTourRequest;
import com.example.QuanLyDuLich.dto.Request.TimKiemTourRequest;
import com.example.QuanLyDuLich.dto.Request.UpdateTourRequest;
import com.example.QuanLyDuLich.dto.Respone.TourRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TourService {
    TourMapper tourMapper;
    TourRepository tourRepository;
    KhuyenMaiRepository khuyenMaiRepository;
    LichTrinhRepository lichTrinhRepository;
    @PreAuthorize("hasRole('ADMIN')")
    public TourRespone createTour(CreateTourRequest request)
    {
        Tour tour = tourMapper.toTour(request);
        if(request.getMalichtrinh()!=null&&!request.getMalichtrinh().isEmpty())
        {
            var khuyenMais = khuyenMaiRepository.findAllById(request.getMakhuyenmais());
            LichTrinh lichtrinh = lichTrinhRepository.findById(request.getMalichtrinh())
                    .orElseThrow(() -> new AppExceptions(ErrorCode.LICHTRINH_NOTEXIST));
            tour.setThongtinlichtrinh(lichtrinh);
        tour.setCackhuyenmai(new HashSet<>(khuyenMais));
        }
        return tourMapper.toTourRespone(tourRepository.save(tour));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public TourRespone updateTour(UpdateTourRequest request)
    {
        Tour tour = tourRepository.findById(request.getId()).orElseThrow(
                ()-> new AppExceptions(ErrorCode.TOUR_NOTEXTIST));

        tourMapper.updateTour(tour,request);
        var khuyenMais = khuyenMaiRepository.findAllById(request.getMakhuyenmais());
        LichTrinh lichtrinh = lichTrinhRepository.findById(request.getMalichtrinh())
                .orElseThrow(() -> new AppExceptions(ErrorCode.LICHTRINH_NOTEXIST));
        tour.setThongtinlichtrinh(lichtrinh);
        tour.setCackhuyenmai(new HashSet<>(khuyenMais));
        return tourMapper.toTourRespone(tourRepository.save(tour));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void DeleteTour(String id)
    {
        Tour tour= tourRepository.findById(id).
                orElseThrow(()->new AppExceptions(ErrorCode.TOUR_NOTEXTIST));
        tourRepository.delete(tour);
    }
    @PreAuthorize("authentication.principal.claims['status'] == true")
    public TourRespone getOneTour(String id)
    {
        Tour tour = tourRepository.findById(id).orElseThrow(
                ()-> new AppExceptions(ErrorCode.TOUR_NOTEXTIST)
        );
        return tourMapper.toTourRespone(tour);
    }
    @PreAuthorize("authentication.principal.claims['status'] == true")
    public List<TourRespone> getAllTour()   {
        List<Tour> tours= tourRepository.findAll();
        return tours.stream().map(tourMapper::toTourRespone).toList();
    }
    @PreAuthorize("authentication.principal.claims['status'] == true")
    public List<TourRespone> getFindingTour(TimKiemTourRequest request)
    {
        List<Tour> tours= tourRepository.findAll();
        List<Tour> result= new ArrayList<>();
        for(Tour tour :tours)
        {
            if (tour.getKhuvuc()==request.getKhuvuc()&&
                    tour.getTheloai()==request.getTheloai()&&
                    tour.getTinhthanh()== request.getTinhthanh()&&(
                    tour.getGiaban().compareTo(request.getGiaban())<0&&
                            tour.getGiaban().compareTo(request.getGiaban())==0)
                    && ChronoUnit.DAYS.between(tour.getThongtinlichtrinh().getThoigiandi(),tour.getThongtinlichtrinh().getThoigianve())<= request.getNgay())
            {
                result.add(tour);
            }
        }
        return result.stream().map(tourMapper::toTourRespone).toList();
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void autoXoaCacTourhethan()
    {
        tourRepository.deleteByThongtinlichtrinh_ThoigiandiBefore(LocalDate.now());
    }
}
