package com.semicolon.africa.lowdataedubackend.service;

import com.semicolon.africa.lowdataedubackend.data.repository.LessonRepository;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateLessonForStudentLearningRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateLessonForStudentLearningResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class LessonServiceImplTest {

    @Autowired
    private LessonService lessonService;
    @Autowired
    private LessonRepository lessonRepository;

    @BeforeEach
    public void setUp() {
        lessonRepository.deleteAll();
    }

    @Test
    public void testThatLessonCanBeCreatedForStudentLearning () {
        CreateLessonForStudentLearningRequest createLessonRequest = new CreateLessonForStudentLearningRequest();
        createLessonRequest.setStudentId(1L);
        createLessonRequest.setLessonId(1L);
        createLessonRequest.setDescription("Mathematics");
        createLessonRequest.setTitle("General Math");
        createLessonRequest.setStatus("In progress");

        CreateLessonForStudentLearningResponse createLessonForStudentLearningResponse = lessonService.lesson(createLessonRequest);
        assertNotNull(createLessonForStudentLearningResponse);
        assertEquals("Lesson In Progress", createLessonForStudentLearningResponse.getMessage());
    }

}