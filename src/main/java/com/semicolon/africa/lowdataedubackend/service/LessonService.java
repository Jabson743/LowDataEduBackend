package com.semicolon.africa.lowdataedubackend.service;

import com.semicolon.africa.lowdataedubackend.dto.request.CreateLessonForStudentLearningRequest;
import com.semicolon.africa.lowdataedubackend.dto.response.CreateLessonForStudentLearningResponse;

public interface LessonService {

    CreateLessonForStudentLearningResponse lesson (CreateLessonForStudentLearningRequest createLessonForStudentLearningRequest);
}
