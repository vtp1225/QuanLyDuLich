package com.example.QuanLyDuLich.Configuration;

import com.example.QuanLyDuLich.Entity.Role;
import com.example.QuanLyDuLich.Entity.User;
import com.example.QuanLyDuLich.Repository.RoleRepository;
import com.example.QuanLyDuLich.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationConfig {
    @Autowired
    PasswordEncoder passwordEncoder ;
    @Autowired
    RoleRepository roleRepository;
    @Transactional
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {

        List<String> admin = new ArrayList<>();
        admin.add("ADMIN");
        var roles=roleRepository.findAllById(admin);
        Hibernate.initialize(roles);
        for (Role role : roles) {
            Hibernate.initialize(role.getPermissions());
        }
        return args -> {
            if(!userRepository.existsBytendangnhap("admin")){

                User user=User.builder()
                        .tendangnhap("admin")
                        .email("adminQLDL@gmail.com")
                        .password(passwordEncoder.encode("admin"))
                        .roles(new HashSet<>(roles))
                        .build();
                userRepository.save(user);
                log.warn("Ban vua khoi tao thanh cong tai khoan admin mac dinh, hay vao vao chinh sua email va password");
            }
        };
    }

}
