package com.sufiyan.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sufiyan.dto.SingleStudentDto;
import com.sufiyan.dto.StudentLeaveDto;
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
	
	@PostMapping("/leave")
	public ResponseEntity<?> applyLeave(@RequestBody StudentLeaveDto studentLeaveDto){
		StudentLeaveDto submittedStudentLeave = studentService.applyLeave(studentLeaveDto);
		if(submittedStudentLeave == null)
			return new ResponseEntity<>("Something went wrong...", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(submittedStudentLeave);
	}
	
	@GetMapping("/leave/{id}")
	public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeaveByStudent(@PathVariable Long id){
		List<StudentLeaveDto> fetchAllLeave = studentService.getAllAppliedLeaveByStudent(id);
		if(fetchAllLeave.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(fetchAllLeave);
	}
}
