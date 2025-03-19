package com.semicolon.africa.lowdataedubackend.controller;

import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentLoginRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentRegisterRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentResetPasswordRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.ApiResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentLoginResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentRegisterResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentResetPasswordResponse;
import com.semicolon.africa.lowdataedubackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("register")
    public ResponseEntity<?> register (@RequestBody CreateStudentRegisterRequest createStudentRegisterRequest) {
        try {
            CreateStudentRegisterResponse response = studentService.register(createStudentRegisterRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody CreateStudentLoginRequest createStudentLoginRequest) {
        try {
            CreateStudentLoginResponse response = studentService.login(createStudentLoginRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/reset")
    public ResponseEntity<?> reset (@RequestBody CreateStudentResetPasswordRequest createStudentResetPasswordRequest) {
        try {
            CreateStudentResetPasswordResponse response = studentService.reset(createStudentResetPasswordRequest);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
