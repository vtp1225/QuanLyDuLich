package com.example.QuanLyDuLich.Entity;


import com.example.QuanLyDuLich.enums.TheLoai;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

v
public class Tour {
    @Id
    String id;
    String tentour;
    String tinhthanh;
    String khuvuc;
    String anhminhhoa;
    @Enumerated(EnumType.STRING)
    TheLoai theloai;
    @Column(precision = 10,scale = 2)
    BigDecimal giagoc;
    String gioithieu;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idlichtrinh",referencedColumnName = "idlichtrinh",nullable = false)
    LichTrinh thongtinlichtrinh;
    @ManyToMany
    Set<KhuyenMai> cackhuyenmai;
    @Column(precision = 10,scale = 2)
    BigDecimal giaban;
    @Transient
    public BigDecimal getGiaban()
    {
        BigDecimal giaban=this.giagoc;
        if(cackhuyenmai!=null && !cackhuyenmai.isEmpty())
            for(KhuyenMai k:this.cackhuyenmai) {
                if (k.getThoihan().isAfter(LocalDate.now())) {
                    giaban = giaban.subtract(giaban.multiply(BigDecimal.valueOf(k.getPhantramkm())));
                }
            }
        return giaban;
    }
}

