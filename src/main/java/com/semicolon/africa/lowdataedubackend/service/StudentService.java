package com.semicolon.africa.lowdataedubackend.service;

import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentLoginRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentRegisterRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentResetPasswordRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentLoginResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentRegisterResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentResetPasswordResponse;

public interface StudentService {

    CreateStudentRegisterResponse register (CreateStudentRegisterRequest createStudentRegisterRequest);

    CreateStudentLoginResponse login (CreateStudentLoginRequest createStudentLoginRequestRequest);

    CreateStudentResetPasswordResponse reset (CreateStudentResetPasswordRequest createStudentResetPasswordRequest);
}
