package com.example.QuanLyDuLich.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${jwt.signerKey}")
    private  String signerKey;
    private final String[] PUBLIC_ENPOINT ={"/users","/auth/token","/auth/introspect","/roles","permissions"};
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request
                ->request.requestMatchers(HttpMethod.POST,PUBLIC_ENPOINT)
                .permitAll()
                .requestMatchers(HttpMethod.GET,"/users").permitAll()
                .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2
                ->oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint( new JwtAuthenticationEntry()))
        ;
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();

    }

    @Bean
    public JwtDecoder jwtDecoder(){
        SecretKeySpec spec=new SecretKeySpec(signerKey.getBytes(StandardCharsets.UTF_8),"HS512");
        return NimbusJwtDecoder.withSecretKey(spec).macAlgorithm(MacAlgorithm.HS512).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter()
    {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter= new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter jwtAuthenticationConverter =new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}

