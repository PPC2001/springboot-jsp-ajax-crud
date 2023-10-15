package com.springboot.ajax.service;

import java.util.List;

import com.springboot.ajax.exception.StudentException;
import com.springboot.ajax.model.Student;

public interface StudentService {
	List<Student> getAllStudent() throws StudentException;

	Student createStudent(Student student) throws StudentException;

	Student updateStudent(long id, Student student) throws StudentException;

	void deleteStudent(long id) throws StudentException;

	Student editStudent(long id) throws StudentException;
}
