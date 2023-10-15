package com.springboot.ajax.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ajax.exception.StudentException;
import com.springboot.ajax.model.Student;
import com.springboot.ajax.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudent() throws StudentException {
		try {
			List<Student> students = studentRepository.findAll();
			logger.info("Retrieved all students");
			return students;
		} catch (Exception e) {
			logger.error("Error while retrieving all students", e);
			throw new StudentException("Error while retrieving all students" + e);
		}
	}

	@Override
	public Student createStudent(Student student) throws StudentException {
		try {
			Student createdStudent = studentRepository.save(student);
			logger.info("Created student with ID: " + createdStudent.getId());
			return createdStudent;
		} catch (Exception e) {
			logger.error("Error while creating student", e);
			throw new StudentException("Error while creating student" + e);
		}
	}

	@Override
	public Student updateStudent(long id, Student student) throws StudentException {
		try {
			student.setId(id);
			Student updatedStudent = studentRepository.save(student);
			logger.info("Updated student with ID: " + id);
			return updatedStudent;
		} catch (Exception e) {
			logger.error("Error while updating student with ID: " + id, e);
			throw new StudentException("Error while updating student with ID: " + e);
		}
	}

	@Override
	public void deleteStudent(long id) throws StudentException {
		try {
			studentRepository.deleteById(id);
			logger.info("Deleted student with ID: " + id);
		} catch (Exception e) {
			logger.error("Error while deleting student with ID: " + id, e);
			throw new StudentException("Error while deleting student with ID: " + e);
		}
	}

	@Override
	public Student editStudent(long id) throws StudentException {
		try {
			Optional<Student> student = studentRepository.findById(id);
			if (student.isPresent()) {
				logger.info("Retrieved student with ID: " + id);
				return student.get();
			} else {
				throw new StudentException("Student not found with ID: " + id);
			}
		} catch (Exception e) {
			logger.error("Exception in editStudent", e);
			throw new StudentException("Exception :: StudentServiceImpl :: editStudent :: " + e);
		}
	}

}
