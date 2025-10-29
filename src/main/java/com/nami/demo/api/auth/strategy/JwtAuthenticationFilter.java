package com.nami.demo.api.auth.strategy;

import com.nami.demo.api.auth.service.JwtUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtStrategy jwtStrategy;
    private final JwtUserDetailsService jwtUserDetailsService;

    public JwtAuthenticationFilter(JwtStrategy jwtStrategy, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtStrategy = jwtStrategy;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        String authHeader = request.getHeader("Authorization");

        // header con Bearer
        if (authHeader != null && authHeader.startsWith("Bearer "))
            token = authHeader.substring(7);

        // buscar cookie
        if (token == null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("Nami_Auth_Session".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // 🚫 continuar sin autenticar
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer username y validar
        String username = jwtStrategy.extractUsername(token);

        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

        if (!jwtStrategy.isTokenValid(token, userDetails)) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Crear autenticación si es válida
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
