package com.hackaboss.PruebaTenica4Final.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/agency/personas").permitAll()
                .requestMatchers("/agency/personas/{id}").permitAll()
                .requestMatchers("/agency/hotels").permitAll()
                .requestMatchers("/agency/hotels/{id}").permitAll()
                .requestMatchers("/agency/rooms").permitAll()
                .requestMatchers("/agency/rooms/{id}").permitAll()
                .requestMatchers("/agency/flights").permitAll()
                .requestMatchers("/agency/flights/{id}").permitAll()
                .requestMatchers("/agency/reservas").permitAll()
                .requestMatchers("/agency/reservas/{id}").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .build();
    }




}
