package com.example.QuanLyDuLich.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATE_EXCEPTION(9999,"Toi Yeu Em, Nhung that bai roi", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(9122,"KEY NOT VALID",HttpStatus.BAD_REQUEST),
    EMAIL_SIZE(123,"Email phai co dinh dang @___.com",HttpStatus.BAD_REQUEST),
    SIZE_PASSWORD(123,"Pass phai co du {min} ky tu",HttpStatus.BAD_REQUEST),
    NAME_EXISTED(1001,"Ten dang nhap da ton tai",HttpStatus.BAD_REQUEST),
    NOT_EXIST(400,"Ten dang nhap khong ton tai",HttpStatus.NOT_FOUND),
    LOGIN_FAIL(500,"Thong tin yeu cau chua chinh xac",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403,"Ban khong co quyen truy cap",HttpStatus.FORBIDDEN),
    PERMISSION_NOTEXIST(404,"KHONG CO PERMISSION NAY",HttpStatus.NOT_FOUND),
    NOT_EXIST_ROLE(400,"Role nay khong ton tai",HttpStatus.NOT_FOUND),
    DATE_VALID(123,"Ban chua du {min}+ de vao trang nay :)))",HttpStatus.BAD_REQUEST),
    PASS_VALID(123,"PASSWORD phai bao gom 1 ky tu viet hoa va 1 ky tu dat biet",HttpStatus.BAD_REQUEST),
    TOUR_NOTEXTIST(123456,"Khong ton tai tour nao nhu the",HttpStatus.NOT_FOUND),
    LICHTRINH_NOTEXIST(123456,"Khong ton tai LICH TRINH NAY nao nhu the",HttpStatus.NOT_FOUND)


    ;
    private String message;
    private int code;
    private HttpStatusCode httpStatusCode;
    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode=httpStatusCode;
    }
}
