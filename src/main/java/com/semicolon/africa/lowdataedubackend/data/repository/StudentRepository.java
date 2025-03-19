package com.semicolon.africa.lowdataedubackend.data.repository;

import com.semicolon.africa.lowdataedubackend.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findStudentByStudentId(Long studentId);

}
