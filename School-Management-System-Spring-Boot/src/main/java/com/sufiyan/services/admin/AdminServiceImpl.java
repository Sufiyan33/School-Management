package com.sufiyan.services.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sufiyan.dto.FeeDto;
import com.sufiyan.dto.SingleStudentDto;
import com.sufiyan.dto.StudentDto;
import com.sufiyan.entities.Fee;
import com.sufiyan.entities.User;
import com.sufiyan.enums.UserRole;
import com.sufiyan.exceptions.StudentAlreadyExistsException;
import com.sufiyan.repositories.FeeRepository;
import com.sufiyan.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService {

	private final UserRepository userRepo;
	private final FeeRepository feeRepository;

	public AdminServiceImpl(UserRepository userRepo, FeeRepository feeRepository) {
		this.userRepo = userRepo;
		this.feeRepository = feeRepository;
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
		Optional<User> existingUser = userRepo.findById(id);
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		existingUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
	}

	@Override
	public StudentDto updateStudent(Long id, StudentDto studentDto) {
		Optional<User> existingStudent = userRepo.findById(id);
		if(existingStudent.isPresent()) {
			User user = existingStudent.get();
			user.setName(studentDto.getName());
			user.setFatherName(studentDto.getFatherName());
			user.setMotherName(studentDto.getMotherName());
			user.setGender(studentDto.getGender());
			user.setAddress(studentDto.getAddress());
			user.setDob(studentDto.getDob());
			user.setEmail(studentDto.getEmail());
			user.setStudentClass(studentDto.getStudentClass());
			User updatedStudent = userRepo.save(user);
			StudentDto updateStudentDto = new StudentDto();
			updateStudentDto.setId(updatedStudent.getId());
			return updateStudentDto;
		}
		return null;
	}

	@Override
	public StudentDto payFee(Long studentId, FeeDto feeDto) {
		Optional<User> optionalStudent = userRepo.findById(studentId);
		if(optionalStudent.isPresent()) {
			Fee fee = new Fee();
			fee.setAmount(feeDto.getAmount());
			fee.setMonth(feeDto.getMonth());
			fee.setDescription(feeDto.getDescription());
			fee.setCreationDate(new Date());
			fee.setGivenBy(feeDto.getGivenBy());
			fee.setUser(optionalStudent.get());
			Fee paidFee = feeRepository.save(fee);
			FeeDto paidFeeDto = new FeeDto();
			paidFeeDto.setId(paidFee.getId());
			return paidFeeDto;
		}
			
		return null;
	}
}
