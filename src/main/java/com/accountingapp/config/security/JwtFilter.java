package com.accountingapp.config.security;

import com.accountingapp.dto.error.ErrorResponse;
import com.accountingapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtFilter extends OncePerRequestFilter {
    JwtProvider jwtProvider;
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (shouldSkipValidation(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");
        try {
            if (authorization != null && authorization.startsWith("Bearer")) {
                String token = authorization.replaceAll("Bearer ", "");
                if (!jwtProvider.isTokenValid(token)) {
                    throw new RuntimeException("The token is expired!");
                }
                String username = jwtProvider.getSubject(token);
                if (username != null) {
                    userRepository.findByUsername(username).ifPresent(user ->
                            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, new UserDetailsImpl(user).getAuthorities()))
                    );
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(errorResponse));
        }
    }

    private boolean shouldSkipValidation(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        return requestURI.startsWith("/api/v1/auth/")
                || requestURI.startsWith("/api/v1/transaction/get")
                || requestURI.startsWith("/api/v1/user");
    }
}