package com.accountingapp.service.impl;

import com.accountingapp.dto.request.UserRequest;
import com.accountingapp.dto.request.UserUpdateRequest;
import com.accountingapp.dto.response.UserResponse;
import com.accountingapp.entity.User;
import com.accountingapp.repository.UserRepository;
import com.accountingapp.service.AccountingService;
import com.accountingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AccountingService accountService;

    @Override
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(
                repository.findAll()
                        .stream()
                        .map(user -> modelMapper.map(user, UserResponse.class))
                        .toList()
        );
    }

    @Override
    public ResponseEntity<UserResponse> getById(Long id) {
        User user = findUserById(id);
        return ResponseEntity.ok(modelMapper.map(user, UserResponse.class));
    }

    @Override
    public ResponseEntity<UserResponse> create(UserRequest request) {
        User user = repository.save(
                User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .fullName(request.getFullName())
                        .build()
        );
        accountService.create(user);

        return ResponseEntity.ok(modelMapper.map(user, UserResponse.class));
    }

    @Override
    public ResponseEntity<UserResponse> update(UserUpdateRequest request, Long userId) {
        User user = findUserById(userId);
        user.setUsername(Optional.ofNullable(request.getUsername()).orElse(user.getUsername()));
        user.setFullName(Optional.ofNullable(request.getFullName()).orElse(user.getFullName()));
        repository.save(user);
        return ResponseEntity.ok(modelMapper.map(user, UserResponse.class));
    }

    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
