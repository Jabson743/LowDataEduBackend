package com.semicolon.africa.lowdataedubackend.controller;

import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherLoginRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherRegisterRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherResetPasswordRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.ApiResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherLoginResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherRegisterResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherResetPasswordResponse;
import com.semicolon.africa.lowdataedubackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateTeacherRegisterRequest createTeacherRegisterRequest) {
        try {
            CreateTeacherRegisterResponse  response = teacherService.register(createTeacherRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CreateTeacherLoginRequest createTeacherLoginRequest) {
        try {
            CreateTeacherLoginResponse response = teacherService.login(createTeacherLoginRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping ("/reset")
    public ResponseEntity<?> reset(@RequestBody CreateTeacherResetPasswordRequest createTeacherResetPasswordRequest) {
        try {
            CreateTeacherResetPasswordResponse response = teacherService.reset(createTeacherResetPasswordRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
