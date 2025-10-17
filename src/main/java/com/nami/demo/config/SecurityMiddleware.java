package com.nami.demo.config;

import com.nami.demo.api.auth.handler.AuthAccessDeniedHandler;
import com.nami.demo.api.auth.handler.AuthFailureHandler;
import com.nami.demo.api.auth.strategy.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityMiddleware {
    private final JwtAuthenticationFilter jwtFilter;
    private final AuthAccessDeniedHandler authAccessDeniedHandler;
    private final AuthFailureHandler authFailureHandler;

    public SecurityMiddleware(JwtAuthenticationFilter jwtFilter, AuthAccessDeniedHandler authAccessDeniedHandler, AuthFailureHandler authFailureHandler) {
        this.jwtFilter = jwtFilter;
        this.authAccessDeniedHandler = authAccessDeniedHandler;
        this.authFailureHandler = authFailureHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/**",
                    "/docs/**",
                    "/static/**",
                    "/health/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedHandler(authAccessDeniedHandler)
                .authenticationEntryPoint(authFailureHandler)
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
