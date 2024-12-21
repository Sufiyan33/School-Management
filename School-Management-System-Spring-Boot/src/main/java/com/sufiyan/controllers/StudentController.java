package com.sufiyan.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sufiyan.dto.SingleStudentDto;
import com.sufiyan.services.student.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long id){
		SingleStudentDto singleStudentDto = studentService.getStudentById(id);
		if(singleStudentDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleStudentDto);
	}
}
