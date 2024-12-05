package com.sufiyan.services.admin;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sufiyan.dto.StudentDto;
import com.sufiyan.entities.User;
import com.sufiyan.enums.UserRole;
import com.sufiyan.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService{
	
	private final UserRepository userRepo;
	
	public AdminServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	/*
	 We don't need call by using api, when app start then it will run automatically
	 */
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepo.findByRole(UserRole.ADMIN);
		if(adminAccount == null) {
			User admin = new User();
			admin.setEmail("admin@test.com");
			admin.setName("admin");
			admin.setRole(UserRole.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepo.save(admin);
		}
	}

	@Override
	public StudentDto posStudent(StudentDto studentDto) {
		Optional<User> optionalUser = userRepo.findFirstByEmail(studentDto.getEmail());
		if(optionalUser.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(studentDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setRole(UserRole.STUDENT);
			User createdUser = userRepo.save(user);
			StudentDto createdStudentDto = new StudentDto();
			createdStudentDto.setId(createdUser.getId());
			createdStudentDto.setEmail(createdUser.getEmail());
			return createdStudentDto;
		}
		return null;
	} 
}
