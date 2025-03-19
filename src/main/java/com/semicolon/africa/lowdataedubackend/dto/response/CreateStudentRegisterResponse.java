package com.semicolon.africa.lowdataedubackend.dto.response;

import lombok.Data;

@Data
public class CreateStudentRegisterResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
