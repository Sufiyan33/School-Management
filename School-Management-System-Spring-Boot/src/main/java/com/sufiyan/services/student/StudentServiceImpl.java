package com.sufiyan.services.student;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sufiyan.dto.SingleStudentDto;
import com.sufiyan.dto.StudentLeaveDto;
import com.sufiyan.entities.StudentLeave;
import com.sufiyan.entities.User;
import com.sufiyan.enums.StudentLeaveStatus;
import com.sufiyan.repositories.StudentLeaveRepository;
import com.sufiyan.repositories.UserRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private final UserRepository userRepository;
	private final StudentLeaveRepository leaveRepository;

	public StudentServiceImpl(UserRepository userRepository, StudentLeaveRepository leaveRepository) {
		this.userRepository = userRepository;
		this.leaveRepository = leaveRepository;
	}

	@Override
	public SingleStudentDto getStudentById(Long id) {
		Optional<User> existingUser = userRepository.findById(id);
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		existingUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
	}

	@Override
	public StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto) {
		Optional<User> existingUser = userRepository.findById(studentLeaveDto.getUserId());
		if(existingUser.isPresent()) {
			StudentLeave leave = new StudentLeave();
			leave.setSubject(studentLeaveDto.getSubject());
			leave.setBody(studentLeaveDto.getBody());
			leave.setDate(new Date());
			leave.setStudentLeaveStatus(StudentLeaveStatus.Pending);
			leave.setUser(existingUser.get());
			StudentLeave submittedLeave = leaveRepository.save(leave);
			StudentLeaveDto leaveDto = new StudentLeaveDto();
			leaveDto.setId(submittedLeave.getId());
			return leaveDto;
		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllAppliedLeaveByStudent(Long userId) {

		return leaveRepository.findAllByUserId(userId).stream().map(StudentLeave::getStudentLeaveDto).collect(Collectors.toList());
	}
}
