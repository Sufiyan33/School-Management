package com.sufiyan.services.admin;

import java.util.List;

import com.sufiyan.dto.FeeDto;
import com.sufiyan.dto.SingleStudentDto;
import com.sufiyan.dto.StudentDto;

public interface AdminService {

	StudentDto posStudent(StudentDto studentDto);
	
	List<StudentDto> getAllStudents();
	
	void deleteStudent(Long studentId);

	SingleStudentDto getStudentById(Long id);

	StudentDto updateStudent(Long id, StudentDto studentDto);

	FeeDto payFee(Long studentId, FeeDto feeDto);
}
