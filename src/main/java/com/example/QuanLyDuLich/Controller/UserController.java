package com.example.QuanLyDuLich.Controller;


import com.example.QuanLyDuLich.Entity.User;
import com.example.QuanLyDuLich.Service.UserService;
import com.example.QuanLyDuLich.dto.Request.CreateUserRequest;
import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import com.example.QuanLyDuLich.dto.Request.UpdateUserRequest;
import com.example.QuanLyDuLich.dto.Respone.UserRespone;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    UserService userService;
    @PostMapping
    ApiRespone userCreate (@RequestBody @Valid CreateUserRequest request)
    {
        ApiRespone<UserRespone> api=new ApiRespone<>();
        api.setResult(userService.createUser(request));
        api.setMessage("Thanh Cong");
        return api;
    }
    @GetMapping("/verify")
    ApiRespone verifyUser(@RequestParam String email,@RequestParam String code)
    {
        var verify = userService.verifyUser(email,code);
            return ApiRespone.builder()
                    .code(000)
                    .message(verify)
                    .build();
    }
    @GetMapping
    List<User> getAllUser()
    {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        authentication.getAuthorities().forEach(s -> log.info(s.getAuthority()));

        return userService.getAll();
    }
    @GetMapping("{userID}")
    ApiRespone <UserRespone> getUser(@PathVariable("userID") String id)
    {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        authentication.getAuthorities().forEach(s -> log.info(s.getAuthority()));
        var result= userService.getUser(id);
        return ApiRespone.<UserRespone>builder()
                .result(result)
                .build();
    }
    @GetMapping("/my_info")
    ApiRespone getInfo()
    {
        var result = userService.getMyInfo();
        return ApiRespone.<UserRespone>builder()
                .result(result)
                .build();
    }
    @PutMapping("{userId}")
    ApiRespone updateUser(@RequestBody @Valid UpdateUserRequest request, @PathVariable("userId") String id)
    {
        return ApiRespone.<UserRespone>builder()
                .result(userService.updateUser(request,id))
                .build();
    }
    @DeleteMapping
    String deleteAll()
    {
        if(userService.deleteAll()==true)
            return "Da xoa thanh cong";
        else return "Khong con gif de xoa";
    }
    @DeleteMapping("{UserID}")
    String deleteUser(@PathVariable("UserID") String id)
    {
        userService.deleteUser(id);
        return "Da xoa";
    }
}
