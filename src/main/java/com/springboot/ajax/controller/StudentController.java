package com.springboot.ajax.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.ajax.model.Student;
import com.springboot.ajax.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentService studentService;

	@RequestMapping("/home")
	public ModelAndView getStudent(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("student-home");
		return modelAndView;
	}

	@GetMapping("/student")
	public ResponseEntity<List<Student>> getAllStudent() {
		try {
			List<Student> students = studentService.getAllStudent();
			logger.info("Retrieved all students");
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting all students", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/student")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		logger.info("Inside the createStudent in StudentController" + student.getId());
		try {
			Student createdStudent = studentService.createStudent(student);
			logger.info("Created student with ID: " + createdStudent.getId());
			return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error while creating student", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/edit/{id}")
	public ResponseEntity<Student> editStudent(@PathVariable("id") long id) {
		try {
			Student editedStudent = studentService.editStudent(id);
			logger.info("Retrieved student with ID: " + id);
			return new ResponseEntity<>(editedStudent, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while editing student", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
		try {
			Student updatedStudent = studentService.updateStudent(id, student);
			logger.info("Updated student with ID: " + id);
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while updating student with ID: " + id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
		try {
			studentService.deleteStudent(id);
			logger.info("Deleted student with ID: " + id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while deleting student with ID: " + id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
