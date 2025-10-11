package com.nami.demo.config;

import com.nami.demo.api.auth.service.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {
    private final JwtUserDetailsService jwtUserDetailsService;

    public ApplicationConfig(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        return authentication -> {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();

            UserDetails user = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, user.getPassword()))
                throw new BadCredentialsException("Credenciales invalidas");

            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return jwtUserDetailsService;
    }
}
