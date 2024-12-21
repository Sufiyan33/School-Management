package com.sufiyan.services.student;

import org.springframework.stereotype.Service;

import com.sufiyan.repositories.UserRepository;

@Service
public class StudentServiceImpl {

	private final UserRepository userRepository;
	
	public StudentServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
