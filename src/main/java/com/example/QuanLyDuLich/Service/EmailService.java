package com.example.QuanLyDuLich.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmailService {
     JavaMailSender javaMailSender;
     public String sendEmail(String to, String code) {
         String subject = "Xác thực đăng ký tài khoản";
         String content = "Mã xác thực của bạn là: " + code;
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("quyvo2079@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            javaMailSender.send(message);
            return "Email sent successfully to " + to;
        } catch (Exception e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}
