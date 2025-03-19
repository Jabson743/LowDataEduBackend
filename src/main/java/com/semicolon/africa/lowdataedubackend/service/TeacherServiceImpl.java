package com.semicolon.africa.lowdataedubackend.service;

import com.semicolon.africa.lowdataedubackend.data.model.Student;
import com.semicolon.africa.lowdataedubackend.data.model.Teacher;
import com.semicolon.africa.lowdataedubackend.data.repository.TeacherRepository;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherLoginRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherRegisterRequest;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateTeacherResetPasswordRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateStudentResetPasswordResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherLoginResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherRegisterResponse;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateTeacherResetPasswordResponse;
import com.semicolon.africa.lowdataedubackend.exceptions.EmailAlreadyExistExceptions;
import com.semicolon.africa.lowdataedubackend.exceptions.UserNotFoundException;
import com.semicolon.africa.lowdataedubackend.exceptions.WrongPasswordException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public CreateTeacherRegisterResponse register(CreateTeacherRegisterRequest createTeacherRegisterRequest) {

        Optional<Teacher> existingTeacher = teacherRepository.findByEmail(createTeacherRegisterRequest.getEmail());

        if (existingTeacher.isPresent()) throw new EmailAlreadyExistExceptions ("This Email Already Exists");

        if (createTeacherRegisterRequest.getFirstName().isEmpty()) throw new IllegalArgumentException ("firstName cannot be empty");
        if (createTeacherRegisterRequest.getLastName().isEmpty()) throw new IllegalArgumentException ("lastName cannot be empty");
        if (createTeacherRegisterRequest.getEmail().isEmpty()) throw new IllegalArgumentException ("email cannot be empty");
        if (createTeacherRegisterRequest.getPassword().isEmpty()) throw new IllegalArgumentException ("password cannot be empty");
        if (createTeacherRegisterRequest.getPhoneNumber().isEmpty()) throw new IllegalArgumentException ("phoneNumber cannot be empty");

        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(createTeacherRegisterRequest.getFirstName().trim());
        newTeacher.setLastName(createTeacherRegisterRequest.getLastName().trim());
        newTeacher.setEmail(createTeacherRegisterRequest.getEmail().trim());
        newTeacher.setPassword(createTeacherRegisterRequest.getPassword().trim());
        newTeacher.setPhoneNumber(createTeacherRegisterRequest.getPhoneNumber().trim());
        teacherRepository.save(newTeacher);

        CreateTeacherRegisterResponse createTeacherRegisterResponse = new CreateTeacherRegisterResponse();
        createTeacherRegisterResponse.setMessage("Teacher Registered");
        return createTeacherRegisterResponse;
    }

    @Override
    public CreateTeacherLoginResponse login(CreateTeacherLoginRequest createTeacherLoginRequest) {

        Optional<Teacher> existingTeacher = teacherRepository.findByEmail(createTeacherLoginRequest.getEmail());

        if (existingTeacher.isEmpty()) throw new UserNotFoundException ("User Not Found");
        if (!(existingTeacher.get().getPassword().equals(createTeacherLoginRequest.getPassword()))) throw new WrongPasswordException("Wrong Email Or Password");

        if (createTeacherLoginRequest.getEmail().isEmpty()) throw new IllegalArgumentException ("email cannot be empty");
        if (createTeacherLoginRequest.getPassword().isEmpty()) throw new IllegalArgumentException ("password cannot be empty");

        Teacher teacher = new Teacher();
        teacher.setEmail(createTeacherLoginRequest.getEmail().trim());
        teacher.setPassword(createTeacherLoginRequest.getPassword().trim());
        teacher.setLoggedIn(true);
        teacherRepository.save(teacher);

        CreateTeacherLoginResponse createTeacherLoginResponse = new CreateTeacherLoginResponse();
        createTeacherLoginResponse.setMessage("Login Successful");
        return createTeacherLoginResponse;
    }

    @Override
    public CreateTeacherResetPasswordResponse reset(CreateTeacherResetPasswordRequest createTeacherResetPasswordRequest) {

        Optional<Teacher> passwordReset = teacherRepository.findTeacherByTeacherId(createTeacherResetPasswordRequest.getTeacherId());

        if (passwordReset.isPresent()) {
            Teacher teacher = passwordReset.get();
            String token = UUID.randomUUID().toString();
            teacher.setResetToken(token);
            teacher.setLoggedIn(false);
            teacher.setResetTokenExpiry(LocalDateTime.now().plusMinutes(30));
            teacher.setPassword(createTeacherResetPasswordRequest.getNewPassword());

            CreateTeacherResetPasswordResponse createTeacherResetPasswordResponse = new CreateTeacherResetPasswordResponse();
            createTeacherResetPasswordResponse.setMessage("Reset Successful");
            return createTeacherResetPasswordResponse;
        }
        throw new UserNotFoundException("User Not Found");
    }

}
