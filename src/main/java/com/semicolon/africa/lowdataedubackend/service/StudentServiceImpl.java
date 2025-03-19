package com.semicolon.africa.lowdataedubackend.service;

import com.semicolon.africa.lowdataedubackend.data.model.Student;
import com.semicolon.africa.lowdataedubackend.data.repository.StudentRepository;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentLoginRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentRegisterRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateStudentResetPasswordRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentLoginResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentRegisterResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentResetPasswordResponse;
import com.semicolon.africa.lowdataedubackend.exceptions.EmailAlreadyExistExceptions;
import com.semicolon.africa.lowdataedubackend.exceptions.UserNotFoundException;
import com.semicolon.africa.lowdataedubackend.exceptions.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public CreateStudentRegisterResponse register(CreateStudentRegisterRequest createStudentRegisterRequest) {

        Optional<Student> existingStudent = studentRepository.findByEmail(createStudentRegisterRequest.getEmail());

        if (existingStudent.isPresent()) throw new EmailAlreadyExistExceptions("Email Already Exists");

        if (createStudentRegisterRequest.getFirstName().isEmpty()) throw new IllegalArgumentException("firstName cannot be empty");
        if (createStudentRegisterRequest.getLastName().isEmpty()) throw new IllegalArgumentException("lastName cannot be empty");
        if (createStudentRegisterRequest.getEmail().isEmpty()) throw new IllegalArgumentException("email cannot be empty");
        if (createStudentRegisterRequest.getPassword().isEmpty()) throw new IllegalArgumentException("password cannot be empty");
        if (createStudentRegisterRequest.getUsername().isEmpty()) throw new IllegalArgumentException("username cannot be empty");
        if (createStudentRegisterRequest.getPhoneNumber().isEmpty()) throw new IllegalArgumentException("phoneNumber cannot be empty");

        Student newStudent = new Student();
        newStudent.setFirstName(createStudentRegisterRequest.getFirstName().trim());
        newStudent.setLastName(createStudentRegisterRequest.getLastName().trim());
        newStudent.setEmail(createStudentRegisterRequest.getEmail().trim());
        newStudent.setUsername(createStudentRegisterRequest.getUsername().trim());
        newStudent.setPhoneNumber(createStudentRegisterRequest.getPhoneNumber().trim());
        newStudent.setPassword(createStudentRegisterRequest.getPassword().trim());
        studentRepository.save(newStudent);

        CreateStudentRegisterResponse createStudentRegisterResponse = new CreateStudentRegisterResponse();
        createStudentRegisterResponse.setMessage("Student Registered Successfully");
        return createStudentRegisterResponse;
        }

    @Override
    public CreateStudentLoginResponse login(CreateStudentLoginRequest createStudentLoginRequestRequest) {

        Optional<Student> existingStudent = studentRepository.findByEmail(createStudentLoginRequestRequest.getEmail());
        if (existingStudent.isEmpty()) throw new UserNotFoundException("Invalid Email Or Password");
        if (!existingStudent.get().getPassword().equals(createStudentLoginRequestRequest.getPassword().trim())) {
            throw new WrongPasswordException("User Not Found");
        }

        if (createStudentLoginRequestRequest.getEmail().isEmpty()) throw new IllegalArgumentException("email cannot be empty");
        if (createStudentLoginRequestRequest.getPassword().isEmpty()) throw new IllegalArgumentException("password cannot be empty");

        Student student = existingStudent.get();
        student.setEmail(createStudentLoginRequestRequest.getEmail());
        student.setPassword(createStudentLoginRequestRequest.getPassword());
        student.setLoggedIn(true);
        studentRepository.save(student);

        CreateStudentLoginResponse createStudentLoginResponse = new CreateStudentLoginResponse();
        createStudentLoginResponse.setMessage("Login Successful");
        return createStudentLoginResponse;
    }

    @Override
    public CreateStudentResetPasswordResponse reset(CreateStudentResetPasswordRequest createStudentResetPasswordRequest) {

        Optional<Student> passwordReset = studentRepository.findStudentByStudentId(createStudentResetPasswordRequest.getStudentId());

        if (passwordReset.isPresent()) {
            Student student = passwordReset.get();
            String token = UUID.randomUUID().toString();
            student.setResetToken(token);
            student.setResetTokenExpiry(LocalDateTime.now().plusMinutes(5));
            student.setPassword(createStudentResetPasswordRequest.getNewPassword());
            studentRepository.save(student);

            CreateStudentResetPasswordResponse createStudentResetPasswordResponse = new CreateStudentResetPasswordResponse();
            createStudentResetPasswordResponse.setMessage("Reset Successful");
            return createStudentResetPasswordResponse;
        }
        throw new UserNotFoundException("User Not Found");
    }

}
