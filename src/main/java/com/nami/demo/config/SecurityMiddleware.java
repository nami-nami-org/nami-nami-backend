package com.nami.demo.config;

import com.nami.demo.api.auth.handler.AuthAccessDeniedHandler;
import com.nami.demo.api.auth.handler.AuthFailureHandler;
import com.nami.demo.api.auth.strategy.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityMiddleware {

    private final JwtAuthenticationFilter jwtFilter;
    private final AuthAccessDeniedHandler authAccessDeniedHandler;
    private final AuthFailureHandler authFailureHandler;

    public SecurityMiddleware(
            JwtAuthenticationFilter jwtFilter,
            AuthAccessDeniedHandler authAccessDeniedHandler,
            AuthFailureHandler authFailureHandler
    ) {
        this.jwtFilter = jwtFilter;
        this.authAccessDeniedHandler = authAccessDeniedHandler;
        this.authFailureHandler = authFailureHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/auth/**", "/docs/**", "/static/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/dish/**").permitAll()
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of(
            "http://localhost:4321",
            "http://localhost:3000",
            "https://nami.luigemp.workers.dev",
            "https://nami-nami.vercel.app",
            "https://*.workers.dev",
            "https://*.vercel.app"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization", "Set-Cookie"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean(CorsConfigurationSource corsConfigurationSource) {
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
