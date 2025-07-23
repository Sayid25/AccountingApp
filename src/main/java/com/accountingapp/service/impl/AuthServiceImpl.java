package com.accountingapp.service.impl;

import com.accountingapp.config.security.JwtProvider;
import com.accountingapp.dto.request.UserLoginRequest;
import com.accountingapp.dto.response.TokenResponse;
import com.accountingapp.entity.User;
import com.accountingapp.service.AuthService;
import com.accountingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authManager;

    @Override
    public ResponseEntity<TokenResponse> login(UserLoginRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userService.findUserByUsername(request.getUsername());

        String token = jwtProvider.generateToken(user.getUsername());

        return ResponseEntity.ok(TokenResponse.builder()
                .login(user.getUsername())
                .accessToken(token)
                .build());
    }
}
