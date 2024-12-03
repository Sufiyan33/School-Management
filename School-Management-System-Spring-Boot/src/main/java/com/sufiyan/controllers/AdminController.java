package com.sufiyan.controllers;

import org.springframework.http.ResponseEntity;
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
	
	public ResponseEntity<?> addStendent(@RequestBody StudentDto studentDto){
		StudentDto createdStudent = adminService.posStudent(studentDto);
	}
}
