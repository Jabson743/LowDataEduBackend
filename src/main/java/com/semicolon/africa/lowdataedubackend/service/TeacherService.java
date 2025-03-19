package com.semicolon.africa.lowdataedubackend.service;

import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherRegisterRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherLoginRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherResetPasswordRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherLoginResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherRegisterResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherResetPasswordResponse;

public interface TeacherService {
    CreateTeacherRegisterResponse register (CreateTeacherRegisterRequest createTeacherRegisterRequest);

    CreateTeacherLoginResponse login (CreateTeacherLoginRequest createTeacherLoginRequest);

    CreateTeacherResetPasswordResponse reset(CreateTeacherResetPasswordRequest createTeacherResetPasswordRequest);
}
