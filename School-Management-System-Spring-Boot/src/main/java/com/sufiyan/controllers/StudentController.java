package com.sufiyan.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sufiyan.services.student.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
}
