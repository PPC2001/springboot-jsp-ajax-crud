package com.springboot.ajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ajax.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
