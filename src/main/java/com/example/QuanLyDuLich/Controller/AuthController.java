package com.example.QuanLyDuLich.Controller;

import com.example.QuanLyDuLich.Service.AuthService;
import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import com.example.QuanLyDuLich.dto.Request.AuthRequest;
import com.example.QuanLyDuLich.dto.Request.IntrospectRequest;
import com.example.QuanLyDuLich.dto.Request.LoggoutRequest;
import com.example.QuanLyDuLich.dto.Respone.AuthRespone;
import com.example.QuanLyDuLich.dto.Respone.IntrospectRespone;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthController {
    AuthService authService;
    @PostMapping("/token")
    ApiRespone<AuthRespone> checkLogin(@RequestBody AuthRequest request)
    {
        AuthRespone result = authService.checkLogin(request);
        return ApiRespone.<AuthRespone>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiRespone<IntrospectRespone> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        IntrospectRespone result = authService.introspect(request);
        return ApiRespone.<IntrospectRespone>builder()
                .result(result)
                .build();
    }
    @PostMapping("/logout")
    ApiRespone loggout(@RequestBody LoggoutRequest request)
            throws ParseException, JOSEException {
         authService.loggout(request);
        return ApiRespone.builder()
                .message("Da logout")
                .build();
    }
}
