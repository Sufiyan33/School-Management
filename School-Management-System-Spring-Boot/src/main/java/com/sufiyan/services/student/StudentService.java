package com.sufiyan.services.student;

import java.util.List;

import com.sufiyan.dto.SingleStudentDto;
import com.sufiyan.dto.StudentLeaveDto;

public interface StudentService {

	SingleStudentDto getStudentById(Long id);

	StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto);

	List<StudentLeaveDto> getAllAppliedLeaveByStudent(Long userId);

}
