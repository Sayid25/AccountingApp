package com.accountingapp.service;

import com.accountingapp.dto.request.UserLoginRequest;
import com.accountingapp.dto.response.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<TokenResponse> login(UserLoginRequest request);
}
