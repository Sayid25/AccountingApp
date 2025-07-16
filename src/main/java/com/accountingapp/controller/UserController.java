package com.accountingapp.controller;

import com.accountingapp.dto.request.UserRequest;
import com.accountingapp.dto.request.UserUpdateRequest;
import com.accountingapp.dto.response.UserResponse;
import com.accountingapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService service;

    @GetMapping("/get-all")
    public ResponseEntity<List<UserResponse>> getAll(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request){
        return service.create(request);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponse> update(@RequestBody UserUpdateRequest request, @PathVariable Long userId){
        return service.update(request, userId);
    }
}
