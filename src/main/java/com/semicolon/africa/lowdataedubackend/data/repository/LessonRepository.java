package com.semicolon.africa.lowdataedubackend.data.repository;

import com.semicolon.africa.lowdataedubackend.data.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Optional<Lesson> findLessonByLessonId(Long lessonId);
}
