package com.javaguidance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static String[] AUTH_WHITELIST= { "/home"};

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests ->
            requests
                    .requestMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest()
                    .authenticated()
                    )
                .cors().configurationSource(request -> {
                   CorsConfiguration cors=new CorsConfiguration();
                   cors.setAllowedOrigins(List.of("http://localhost:3000"));
                   cors.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
                   cors.setAllowedHeaders(List.of("*"));
                   cors.setAllowCredentials(true);
                   return cors;
                }).and()
                .httpBasic()
                ;

       return http.build();
    }

}
