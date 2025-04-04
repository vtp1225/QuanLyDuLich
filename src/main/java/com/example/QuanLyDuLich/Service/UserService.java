package com.example.QuanLyDuLich.Service;

import com.example.QuanLyDuLich.Entity.User;
import com.example.QuanLyDuLich.Exception.AppExceptions;
import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.Mapper.UserMapper;
import com.example.QuanLyDuLich.Repository.RoleRepository;
import com.example.QuanLyDuLich.Repository.UserRepository;
import com.example.QuanLyDuLich.dto.Request.CreateUserRequest;
import com.example.QuanLyDuLich.dto.Request.UpdateUserRequest;
import com.example.QuanLyDuLich.dto.Respone.UserRespone;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    private final RoleRepository roleRepository;
    UserRepository userRepo;
    UserMapper usermap;
    PasswordEncoder passwordEncoder ;
    Set<String> rolename = new HashSet<>();

    public UserRespone createUser(CreateUserRequest request)
    {
        if(userRepo.existsBytendangnhap(request.getTendangnhap()))
            throw new AppExceptions(ErrorCode.NAME_EXISTED);
        User user2= usermap.toUser(request);
        user2.setPassword(passwordEncoder.encode(request.getPassword()));
        String userRole = "USER";
        rolename.add(userRole);
        var dsRole=roleRepository.findAllById(rolename);

        user2.setRoles(new HashSet<>(dsRole));
        user2 = userRepo.save(user2);
        return usermap.toUserRespone(user2);

    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll()
    {
        log.info("Ban da vao duoc getUsers");
        return userRepo.findAll();
    }
    @PostAuthorize("returnObject.tendangnhap == authentication.name")
    public UserRespone getUser(String id){
        log.info("Ban da vao duoc getUser");
        return usermap.toUserRespone(userRepo.findById(id).
                orElseThrow(()-> new AppExceptions(ErrorCode.UNCATE_EXCEPTION)));
    }

   @PostAuthorize("returnObject.tendangnhap == authentication.name")
    public UserRespone getMyInfo()
    {
        var context= SecurityContextHolder.getContext();
        String tendangnhap= context.getAuthentication().getName();
        return usermap.toUserRespone(userRepo.findBytendangnhap(tendangnhap).orElseThrow(()-> new AppExceptions(ErrorCode.NOT_EXIST)));
    }

    @PostAuthorize("returnObject.tendangnhap == authentication.name")
    public UserRespone updateUser(UpdateUserRequest request, String id)
    {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new AppExceptions(ErrorCode.NOT_EXIST));
        usermap.updateUser(user,request);
       user.setPassword(passwordEncoder.encode(request.getPassword()));
        var rolelist=roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(rolelist));
        user = userRepo.save(user);
        return usermap.toUserRespone(user);

    }
    public boolean deleteAll()
    {
        if(userRepo.count()==0)
            return false;
        else {
            userRepo.deleteAll();
            return true;
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String id)
    {
        User user = userRepo.findById(id).orElseThrow(()-> new AppExceptions(ErrorCode.NAME_EXISTED));
        userRepo.delete(user);
    }
}

