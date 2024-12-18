package com.sufiyan.services.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sufiyan.dto.SingleStudentDto;
import com.sufiyan.dto.StudentDto;
import com.sufiyan.entities.User;
import com.sufiyan.enums.UserRole;
import com.sufiyan.exceptions.StudentAlreadyExistsException;
import com.sufiyan.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService {

	private final UserRepository userRepo;

	public AdminServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	/*
	 * We don't need call by using api, when app start then it will run
	 * automatically
	 */
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepo.findByRole(UserRole.ADMIN);
		if (adminAccount == null) {
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
		// check if student already exist
		Optional<User> optionalUser = userRepo.findFirstByEmail(studentDto.getEmail());
		if (optionalUser.isPresent()) {
			throw new StudentAlreadyExistsException("Student with this email already exists.");
		}

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

	@Override
	public List<StudentDto> getAllStudents() {
		return userRepo.findAllByRole(UserRole.STUDENT).stream().map(User::getStudentDto).collect(Collectors.toList());
	}

	@Override
	public void deleteStudent(Long studentId) {
		userRepo.deleteById(studentId);
	}

	@Override
	public SingleStudentDto getStudentById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
