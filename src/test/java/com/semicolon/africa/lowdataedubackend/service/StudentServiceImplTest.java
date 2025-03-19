package com.semicolon.africa.lowdataedubackend.service;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    public void testThatStudentCanRegister() {
        CreateStudentRegisterRequest createStudentRegisterRequest = new CreateStudentRegisterRequest();
        createStudentRegisterRequest.setFirstName("John");
        createStudentRegisterRequest.setLastName("Emmanuel");
        createStudentRegisterRequest.setEmail("john1@email.com");
        createStudentRegisterRequest.setPassword("John123");
        createStudentRegisterRequest.setUsername("JohnEmma");
        createStudentRegisterRequest.setPhoneNumber("09065115377");

        CreateStudentRegisterResponse createStudentRegisterResponse = studentService.register(createStudentRegisterRequest);
        assertNotNull(createStudentRegisterResponse);
        assertEquals("Student Registered Successfully", createStudentRegisterResponse.getMessage());
    }

    @Test
    public void testThatStudentCannotRegisterWithAlreadyRegisteredEmail() {
        CreateStudentRegisterRequest createStudentRegisterRequest = new CreateStudentRegisterRequest();
        createStudentRegisterRequest.setFirstName("John");
        createStudentRegisterRequest.setLastName("Emmanuel");
        createStudentRegisterRequest.setEmail("john1@email.com");
        createStudentRegisterRequest.setPassword("John123");
        createStudentRegisterRequest.setUsername("JohnEmma");
        createStudentRegisterRequest.setPhoneNumber("09065115377");
        studentService.register(createStudentRegisterRequest);

        CreateStudentRegisterRequest createStudentRegisterRequest2 = new CreateStudentRegisterRequest();
        createStudentRegisterRequest2.setFirstName("John");
        createStudentRegisterRequest2.setLastName("Jane");
        createStudentRegisterRequest2.setEmail("john1@email.com");
        createStudentRegisterRequest2.setPassword("Jane1234");
        createStudentRegisterRequest2.setUsername("JaneEmma");
        createStudentRegisterRequest2.setPhoneNumber("09065115399");

        assertThrows(EmailAlreadyExistExceptions.class, () -> studentService.register(createStudentRegisterRequest));
    }

    @Test
    public void testThatAlreadyRegisteredStudentCanLoginWithTheSameEmailAndPassword() {
        CreateStudentRegisterRequest createStudentRegisterRequest = new CreateStudentRegisterRequest();
        createStudentRegisterRequest.setFirstName("John");
        createStudentRegisterRequest.setLastName("Emmanuel");
        createStudentRegisterRequest.setEmail("john1@email.com");
        createStudentRegisterRequest.setPassword("John123");
        createStudentRegisterRequest.setUsername("JohnEmma");
        createStudentRegisterRequest.setPhoneNumber("09065115377");

        CreateStudentRegisterResponse createStudentRegisterResponse = studentService.register(createStudentRegisterRequest);
        assertNotNull(createStudentRegisterResponse);
        assertEquals("Student Registered Successfully", createStudentRegisterResponse.getMessage());

        CreateStudentLoginRequest createStudentLoginRequest = new CreateStudentLoginRequest();
        createStudentLoginRequest.setEmail("john1@email.com");
        createStudentLoginRequest.setPassword("John123");

        CreateStudentLoginResponse createStudentLoginResponse = studentService.login(createStudentLoginRequest);
        assertNotNull(createStudentLoginResponse);
        assertEquals("Login Successful", createStudentLoginResponse.getMessage());
    }

    @Test
    public void testThatAlreadyRegisteredStudentCannotLoginWithWrongEmail() {
        CreateStudentRegisterRequest createStudentRegisterRequest = new CreateStudentRegisterRequest();
        createStudentRegisterRequest.setFirstName("John");
        createStudentRegisterRequest.setLastName("Emmanuel");
        createStudentRegisterRequest.setEmail("john1@email.com");
        createStudentRegisterRequest.setPassword("John123");
        createStudentRegisterRequest.setUsername("JohnEmma");
        createStudentRegisterRequest.setPhoneNumber("09065115377");

        CreateStudentRegisterResponse createStudentRegisterResponse = studentService.register(createStudentRegisterRequest);
        assertNotNull(createStudentRegisterResponse);
        assertEquals("Student Registered Successfully", createStudentRegisterResponse.getMessage());

        CreateStudentLoginRequest createStudentLoginRequest = new CreateStudentLoginRequest();
        createStudentLoginRequest.setEmail("john@email.com");
        createStudentLoginRequest.setPassword("John123");

        assertThrows(UserNotFoundException.class, () -> studentService.login(createStudentLoginRequest));
    }

    @Test
    public void testThatAlreadyRegisteredStudentCannotLoginWithWrongPassword() {
        CreateStudentRegisterRequest createStudentRegisterRequest = new CreateStudentRegisterRequest();
        createStudentRegisterRequest.setFirstName("John");
        createStudentRegisterRequest.setLastName("Emmanuel");
        createStudentRegisterRequest.setEmail("john1@email.com");
        createStudentRegisterRequest.setPassword("John12");
        createStudentRegisterRequest.setUsername("JohnEmma");
        createStudentRegisterRequest.setPhoneNumber("09065115377");

        CreateStudentRegisterResponse createStudentRegisterResponse = studentService.register(createStudentRegisterRequest);
        assertNotNull(createStudentRegisterResponse);
        assertEquals("Student Registered Successfully", createStudentRegisterResponse.getMessage());

        CreateStudentLoginRequest createStudentLoginRequest = new CreateStudentLoginRequest();
        createStudentLoginRequest.setEmail("john1@email.com");
        createStudentLoginRequest.setPassword("John123");

        assertThrows(WrongPasswordException.class, () -> studentService.login(createStudentLoginRequest));
    }

    @Test
    public void testThatStudentCanResetPassword() {
        CreateStudentRegisterRequest createStudentRegisterRequest = new CreateStudentRegisterRequest();
        createStudentRegisterRequest.setFirstName("John");
        createStudentRegisterRequest.setLastName("Emmanuel");
        createStudentRegisterRequest.setEmail("john1@email.com");
        createStudentRegisterRequest.setPassword("John12");
        createStudentRegisterRequest.setUsername("JohnEmma");
        createStudentRegisterRequest.setPhoneNumber("09065115377");

        CreateStudentRegisterResponse createStudentRegisterResponse = studentService.register(createStudentRegisterRequest);
        assertNotNull(createStudentRegisterResponse);
        assertEquals("Student Registered Successfully", createStudentRegisterResponse.getMessage());

        CreateStudentResetPasswordRequest createStudentResetPasswordRequest = new CreateStudentResetPasswordRequest();
        createStudentResetPasswordRequest.setToken("5");
        createStudentResetPasswordRequest.setNewPassword("John123");
        createStudentResetPasswordRequest.setStudentId(12L);

        CreateStudentResetPasswordResponse createStudentResetPasswordResponse = studentService.reset(createStudentResetPasswordRequest);
        assertNotNull(createStudentResetPasswordResponse);
        assertEquals("Reset Successful", createStudentResetPasswordResponse.getMessage());
    }

}

