package com.semicolon.africa.lowdataedubackend.dto.request;

import lombok.Getter;
import lombok.Setter;


public class CreateStudentResetPasswordRequest {
   private Long studentId;
   private String token;
   private String newPassword;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
