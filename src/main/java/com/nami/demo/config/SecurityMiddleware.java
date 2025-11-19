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
                .anyRequest().permitAll()
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
        config.setAllowedOriginPatterns(List.of("*")); // cualquier origen
        config.setAllowedMethods(List.of("*"));        // cualquier método
        config.setAllowedHeaders(List.of("*"));        // cualquier header
        config.setExposedHeaders(List.of("*"));        // expone todos los headers
        config.setAllowCredentials(true);             // cookies habilitadas
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
