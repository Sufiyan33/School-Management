package com.sufiyan.services.student;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sufiyan.dto.SingleStudentDto;
import com.sufiyan.entities.User;
import com.sufiyan.repositories.UserRepository;

@Service
public class StudentServiceImpl implements StudentService{

	private final UserRepository userRepository;
	
	public StudentServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public SingleStudentDto getStudentById(Long id) {
		Optional<User> existingUser = userRepository.findById(id);
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		existingUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
	}
}
