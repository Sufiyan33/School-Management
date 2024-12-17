package com.sufiyan.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sufiyan.dto.StudentDto;
import com.sufiyan.exceptions.StudentAlreadyExistsException;
import com.sufiyan.services.admin.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@PostMapping("/student")
	public ResponseEntity<?> addStendent(@RequestBody StudentDto studentDto){
		try {
			StudentDto createdStudent = adminService.posStudent(studentDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
		}catch(StudentAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong...");
		}
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents(){
		List<StudentDto> allStudents = adminService.getAllStudents();
		return ResponseEntity.ok(allStudents);
	}
	
	@DeleteMapping("/student/{studetnId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
		adminService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}
}
