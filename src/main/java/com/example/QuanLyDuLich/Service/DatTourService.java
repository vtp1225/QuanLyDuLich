package com.example.QuanLyDuLich.Service;

import com.example.QuanLyDuLich.Entity.DatTour;
import com.example.QuanLyDuLich.Mapper.DatTourMapper;
import com.example.QuanLyDuLich.Repository.DatTourRepository;
import com.example.QuanLyDuLich.dto.Request.DatTourRequest;
import com.example.QuanLyDuLich.dto.Respone.DatTourRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DatTourService {
    DatTourRepository datTourRepository;
    DatTourMapper mapper;
    public  DatTourRespone datour(DatTourRequest datTourRequest)
    {
        DatTour dt = mapper.toDatTour(datTourRequest);
        if(datTourRepository.existsById(datTourRequest.getMadattour()))
        {
            return null;
        }
        return mapper.toDatTourRespone(datTourRepository.save(dt));
    }
}
