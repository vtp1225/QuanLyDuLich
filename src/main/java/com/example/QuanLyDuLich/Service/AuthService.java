package com.example.QuanLyDuLich.Service;

import com.example.QuanLyDuLich.Entity.LoggoutToken;
import com.example.QuanLyDuLich.Entity.User;
import com.example.QuanLyDuLich.Exception.AppExceptions;
import com.example.QuanLyDuLich.Exception.ErrorCode;
import com.example.QuanLyDuLich.Repository.LoggoutTokenRepository;
import com.example.QuanLyDuLich.Repository.UserRepository;

import com.example.QuanLyDuLich.dto.Request.AuthRequest;
import com.example.QuanLyDuLich.dto.Request.IntrospectRequest;
import com.example.QuanLyDuLich.dto.Request.LoggoutRequest;
import com.example.QuanLyDuLich.dto.Respone.AuthRespone;
import com.example.QuanLyDuLich.dto.Respone.IntrospectRespone;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthService {
    @NonFinal
    @Value("${jwt.signerKey}")
    private String signerKey;
    UserRepository userRepository;
    LoggoutTokenRepository loggoutrepo;
    public AuthRespone checkLogin(AuthRequest request)
    {
        System.out.println(request.getEmail());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByemail(request.getEmail()).
                orElseThrow(()->new AppExceptions(ErrorCode.UNCATE_EXCEPTION));

        boolean bool = passwordEncoder.matches(request.getPassword(),user.getPassword());

        if(bool==false)
            throw new AppExceptions(ErrorCode.LOGIN_FAIL);
        var token=generateToken(user);
        return AuthRespone.builder()
                .authresult(bool)
                .token(token)
                .build();

    }
    public IntrospectRespone introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        boolean bool=true;
      try {
          verifiedToken(request.getToken());
      }catch (AppExceptions exceptions)
      {
          bool=false;
      }
        return IntrospectRespone .builder()
                .valid(bool)
                .build();
    }
    public void loggout(LoggoutRequest request) throws ParseException, JOSEException {
        var signedTK= verifiedToken(request.getToken());
        String idtoken= signedTK.getJWTClaimsSet().getJWTID();
        Date dayhethan=signedTK.getJWTClaimsSet().getExpirationTime();
        LoggoutToken loggoutToken= new LoggoutToken(idtoken,dayhethan);
        loggoutrepo.save(loggoutToken);
    }
    private SignedJWT verifiedToken(String token) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier= new MACVerifier(signerKey.getBytes(StandardCharsets.UTF_8));
        SignedJWT signedJWT= SignedJWT.parse(token);
        Date datehethan= signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified= signedJWT.verify(jwsVerifier);
        if(loggoutrepo.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppExceptions(ErrorCode.UNCATE_EXCEPTION);
        if(verified&&datehethan.after(new Date()))
            return signedJWT;
        else  throw new AppExceptions(ErrorCode.UNAUTHORIZED);
    }
    private String generateToken(User user)
    {
        JWSHeader jwsHeader=new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
                .subject(user.getTendangnhap()) //ten nguoi su dung
                .issuer("STUTralvel.com")// ten mien
                .jwtID(UUID.randomUUID().toString())
                .issueTime(new Date())//thoi gian tao
                .expirationTime(new Date(Instant.now().plus(5, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope",getScope(user))
                .claim("status",user.isTrangthai())
                .build();
        Payload payload=new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject =new JWSObject(jwsHeader,payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes(StandardCharsets.UTF_8)));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("khong the tao token",e);
            throw new RuntimeException(e);
        }

    }
    private String getScope(User user)
    {
        StringJoiner stringJoiner=new StringJoiner(" ");
        if(!user.getRoles().isEmpty())
        {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_"+role.getRolename());
                if(!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(
                        permission -> {
                    stringJoiner.add(permission.getName());
                });
            });
        }
        return stringJoiner.toString();
    }
}
