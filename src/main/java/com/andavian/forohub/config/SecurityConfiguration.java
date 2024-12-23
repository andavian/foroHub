package com.andavian.forohub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desactiva CSRF utilizando la API moderna.
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // Permitir todas las solicitudes.
        return http.build();
    }
}
