package com.ssspamqe.roomrent.security;


import com.ssspamqe.roomrent.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader(HEADER_NAME);

        if (!isValidHeader(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = parserJwt(authHeader);
        var username = jwtService.extractUsername(jwt);
        if (!username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
            setSecurityContext(jwt, request);
        }

        filterChain.doFilter(request, response);
    }

    private void setSecurityContext(String jwt, HttpServletRequest request) {
        String username = jwtService.extractUsername(jwt);
        var securityUserDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(jwt, securityUserDetails)) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    securityUserDetails,
                    null,
                    securityUserDetails.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        }
    }

    private boolean isValidHeader(String authHeader) {
        return authHeader.startsWith(BEARER_PREFIX);
    }

    private String parserJwt(String authHeader) {
        return authHeader.substring(BEARER_PREFIX.length());
    }
}
