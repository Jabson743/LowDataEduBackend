package com.semicolon.africa.lowdataedubackend.service;


import com.semicolon.africa.lowdataedubackend.data.model.Lesson;
import com.semicolon.africa.lowdataedubackend.data.model.Student;
import com.semicolon.africa.lowdataedubackend.data.repository.LessonRepository;
import com.semicolon.africa.lowdataedubackend.dto.request.CreateLessonForStudentLearningRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateLessonForStudentLearningResponse;
import com.semicolon.africa.lowdataedubackend.exceptions.LessonAlreadyExistsExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;


    @Override
    public CreateLessonForStudentLearningResponse lesson(CreateLessonForStudentLearningRequest createLessonForStudentLearningRequest) {

        Optional<Lesson> studentLesson = lessonRepository.findLessonByLessonId(createLessonForStudentLearningRequest.getLessonId());

        if (studentLesson.isPresent()) throw new LessonAlreadyExistsExceptions("Lesson Already Exists");

        Lesson newLesson = new Lesson();
        newLesson.setLessonId(createLessonForStudentLearningRequest.getLessonId());
        newLesson.setStudentId(createLessonForStudentLearningRequest.getStudentId());
        newLesson.setTitle("Mathematics");
        newLesson.setDescription("General Math");
        newLesson.setStatus("In progress");
        lessonRepository.save(newLesson);

        CreateLessonForStudentLearningResponse response = new CreateLessonForStudentLearningResponse();
        response.setMessage("Lesson In Progress");
        return response;
    }
}
