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

import com.sufiyan.dto.FeeDto;
import com.sufiyan.dto.SingleStudentDto;
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
	
	@GetMapping("/student/{id}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long id){
		SingleStudentDto singleStudentDto = adminService.getStudentById(id);
		if(singleStudentDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleStudentDto);
	}
	
	@PostMapping("/student/{id}")
	public ResponseEntity<?> updateStendent(@PathVariable Long id, @RequestBody StudentDto studentDto){
		try {
			StudentDto updatedStudent = adminService.updateStudent(id, studentDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(updatedStudent);
		}catch(StudentAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong...");
		}
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		adminService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/fee/{studentId}")
	public ResponseEntity<?> payFee(@PathVariable Long studentId, @RequestBody FeeDto feeDto){
		try {
			StudentDto createdStudent = adminService.payFee(studentId, feeDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
		}catch(StudentAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong...");
		}
	}
}
