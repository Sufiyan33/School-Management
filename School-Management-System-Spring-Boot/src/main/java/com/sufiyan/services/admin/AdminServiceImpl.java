package com.sufiyan.services.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sufiyan.entities.User;
import com.sufiyan.enums.UserRole;
import com.sufiyan.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl {
	
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
}
