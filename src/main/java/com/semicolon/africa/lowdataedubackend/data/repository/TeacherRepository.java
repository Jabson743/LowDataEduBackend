package com.semicolon.africa.lowdataedubackend.data.repository;

import com.semicolon.africa.lowdataedubackend.data.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findTeacherByTeacherId(Long teacherId);
}
