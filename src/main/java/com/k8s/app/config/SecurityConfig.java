package com.k8s.app.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig { // 스프링 시큐리티에 필요한 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CSRF 설정 Disable
        http.csrf(AbstractHttpConfigurer::disable);
        // JWT 인증 방식 세팅

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**", "/api/ws/**").permitAll()
                        .requestMatchers(String.valueOf(HttpMethod.PATCH),"/api/users/**").authenticated()
                        .requestMatchers(String.valueOf(HttpMethod.POST),"/api/tome/{tomeId}/**").authenticated()
                        .requestMatchers(String.valueOf(HttpMethod.POST),"/api/fromme/**").authenticated()
                        .requestMatchers(String.valueOf(HttpMethod.DELETE),"/api/tome/{tomeId}/**").authenticated()
                        .requestMatchers(String.valueOf(HttpMethod.DELETE),"/api/fromme/**").authenticated()
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("**")); //
            config.setAllowCredentials(true);
            return config;
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws
            Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
