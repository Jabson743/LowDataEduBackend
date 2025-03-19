package com.semicolon.africa.lowdataedubackend.service;

import com.semicolon.africa.lowdataedubackend.data.repository.TeacherRepository;
import com.semicolon.africa.lowdataedubackend.dto.request.*;
import com.semicolon.africa.lowdataedubackend.dto.response.*;
import com.semicolon.africa.lowdataedubackend.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TeacherServiceImplTest {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    public void setUp() {
        teacherRepository.deleteAll();
    }

    @Test
    public void testThatTeacherCanRegister () {
        CreateTeacherRegisterRequest createTeacherRegisterRequest = new CreateTeacherRegisterRequest();
        createTeacherRegisterRequest.setFirstName("Mary");
        createTeacherRegisterRequest.setLastName("Smith");
        createTeacherRegisterRequest.setEmail("mary.smith@gmail.com");
        createTeacherRegisterRequest.setPassword("mary1234");
        createTeacherRegisterRequest.setPhoneNumber("08022880118");

        CreateTeacherRegisterResponse createTeacherRegisterResponse = teacherService.register(createTeacherRegisterRequest);
        assertNotNull(createTeacherRegisterResponse);
        assertEquals("Teacher Registered", createTeacherRegisterResponse.getMessage());
    }

    @Test
    public void testThatTeacherCanLoginWithRightEmailAndPassword () {
        CreateTeacherRegisterRequest createTeacherRegisterRequest = new CreateTeacherRegisterRequest();
        createTeacherRegisterRequest.setFirstName("Mary");
        createTeacherRegisterRequest.setLastName("Smith");
        createTeacherRegisterRequest.setEmail("mary.smith@gmail.com");
        createTeacherRegisterRequest.setPassword("mary1234");
        createTeacherRegisterRequest.setPhoneNumber("08022880118");

        CreateTeacherRegisterResponse createTeacherRegisterResponse = teacherService.register(createTeacherRegisterRequest);
        assertNotNull(createTeacherRegisterResponse);
        assertEquals("Teacher Registered", createTeacherRegisterResponse.getMessage());

        CreateTeacherLoginRequest createTeacherLoginRequest = new CreateTeacherLoginRequest();
        createTeacherLoginRequest.setEmail("mary.smith@gmail.com");
        createTeacherLoginRequest.setPassword("mary1234");

        CreateTeacherLoginResponse createTeacherLoginResponse = teacherService.login(createTeacherLoginRequest);
        assertNotNull(createTeacherLoginResponse);
        assertEquals("Login Successful", createTeacherLoginResponse.getMessage());
    }

    @Test
    public void testThatTeacherCannotLoginWithWrongEmail () {
        CreateTeacherRegisterRequest createTeacherRegisterRequest = new CreateTeacherRegisterRequest();
        createTeacherRegisterRequest.setFirstName("Mary");
        createTeacherRegisterRequest.setLastName("Smith");
        createTeacherRegisterRequest.setEmail("mary.smith@gmail.com");
        createTeacherRegisterRequest.setPassword("mary1234");
        createTeacherRegisterRequest.setPhoneNumber("08022880118");

        CreateTeacherRegisterResponse createTeacherRegisterResponse = teacherService.register(createTeacherRegisterRequest);
        assertNotNull(createTeacherRegisterResponse);
        assertEquals("Teacher Registered", createTeacherRegisterResponse.getMessage());

        CreateTeacherLoginRequest createTeacherLoginRequest = new CreateTeacherLoginRequest();
        createTeacherLoginRequest.setEmail("mary.mith@gmail.com");
        createTeacherLoginRequest.setPassword("mary1234");

        assertThrows(UserNotFoundException.class, () -> teacherService.login(createTeacherLoginRequest));
    }

    @Test
    public void testThatTeacherCannotLoginWithWrongPassword () {
        CreateTeacherRegisterRequest createTeacherRegisterRequest = new CreateTeacherRegisterRequest();
        createTeacherRegisterRequest.setFirstName("Mary");
        createTeacherRegisterRequest.setLastName("Smith");
        createTeacherRegisterRequest.setEmail("mary.smith@gmail.com");
        createTeacherRegisterRequest.setPassword("mary1234");
        createTeacherRegisterRequest.setPhoneNumber("08022880118");

        CreateTeacherRegisterResponse createTeacherRegisterResponse = teacherService.register(createTeacherRegisterRequest);
        assertNotNull(createTeacherRegisterResponse);
        assertEquals("Teacher Registered", createTeacherRegisterResponse.getMessage());

        CreateTeacherLoginRequest createTeacherLoginRequest = new CreateTeacherLoginRequest();
        createTeacherLoginRequest.setEmail("mary.mith@gmail.com");
        createTeacherLoginRequest.setPassword("mary134");

        assertThrows(UserNotFoundException.class, () -> teacherService.login(createTeacherLoginRequest));
    }

    @Test
    public void testThatTeacherCanResetPassword() {
        CreateTeacherRegisterRequest createTeacherRegisterRequest = new CreateTeacherRegisterRequest();
        createTeacherRegisterRequest.setFirstName("Mary");
        createTeacherRegisterRequest.setLastName("Smith");
        createTeacherRegisterRequest.setEmail("mary.smith@email.com");
        createTeacherRegisterRequest.setPassword("mary1234");
        createTeacherRegisterRequest.setPhoneNumber("08022880118");

        CreateTeacherRegisterResponse createTeacherRegisterResponse = teacherService.register(createTeacherRegisterRequest);
        assertNotNull(createTeacherRegisterResponse);
        assertEquals("Teacher Registered", createTeacherRegisterResponse.getMessage());

        CreateTeacherResetPasswordRequest createTeacherResetPasswordRequest = new CreateTeacherResetPasswordRequest();
        createTeacherResetPasswordRequest.setToken("2");
        createTeacherResetPasswordRequest.setNewPassword("mary123");
        createTeacherResetPasswordRequest.setTeacherId(1L);

        CreateTeacherResetPasswordResponse createTeacherResetPasswordResponse = teacherService.reset(createTeacherResetPasswordRequest);
        assertNotNull(createTeacherResetPasswordResponse);
        assertEquals("Reset Successful", createTeacherResetPasswordResponse.getMessage());
    }

}