package com.sufiyan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sufiyan.dto.StudentDto;
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
		StudentDto createdStudent = adminService.posStudent(studentDto);
		if(createdStudent == null)
			return new ResponseEntity<>("something wrong.", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
	}
}
