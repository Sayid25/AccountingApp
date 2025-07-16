package com.accountingapp.service;

import com.accountingapp.dto.request.UserRequest;
import com.accountingapp.dto.request.UserUpdateRequest;
import com.accountingapp.dto.response.UserResponse;
import com.accountingapp.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<UserResponse>> getAll();

    ResponseEntity<UserResponse> getById(Long id);

    ResponseEntity<UserResponse> create(UserRequest request);

    ResponseEntity<UserResponse> update(UserUpdateRequest request, Long userId);

    User findUserById(Long id);
}
