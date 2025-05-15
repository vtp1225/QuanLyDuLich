package com.example.QuanLyDuLich.enums;

public enum TheLoai {
    PHIEU_LUU_MAP_HIEM("Phiêu lưu mạo hiểm"),
    BIEN_DAO("Biển Đảo"),
    NGHI_DUONG("Nghỉ dưỡng spa"),
    VANHOA_LICHSU("Văn hóa lịch Sử"),
    THIEN_NHIEN("Thien nhien phong canh"),
    AM_THUC("Du lịch ẩm thực"),
    MUA_SAM("Du lịch mua sắm"),
    SUC_KHOE("Du lịch chăm sóc sức khỏe"),
    HOI_NGHI("Du lịch hội nghị"),
    TINH_NHAN("Du lịch lãng mạn dành cho cặp đôi")
    ;
    private String chitiet;
    TheLoai(String chitiet)
    {
        this.chitiet=chitiet;
    }

    public String getChitiet() {
        return chitiet;
    }
}
