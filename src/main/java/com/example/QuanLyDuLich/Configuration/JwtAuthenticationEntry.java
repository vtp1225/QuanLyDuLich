package com.example.QuanLyDuLich.Configuration;

import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        ErrorCode errorCode=ErrorCode.LOGIN_FAIL;
        response.setStatus(errorCode.getHttpStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ApiRespone<?> api=  ApiRespone.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        ObjectMapper objectMapper =new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(api));
        response.flushBuffer();
    }
}
