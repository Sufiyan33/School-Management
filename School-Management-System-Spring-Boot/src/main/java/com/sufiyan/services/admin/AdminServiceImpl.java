package com.sufiyan.services.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sufiyan.entities.User;
import com.sufiyan.repositories.UserRepository;

@Service
public class AdminServiceImpl {
	
	private final UserRepository userRepo;
	
	public AdminServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public void createAdminAccount() {
		User adminAccount = new User();
		adminAccount.setEmail("admin@test.com");
		adminAccount.setName("admin");
		adminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
		userRepo.save(adminAccount);
	} 
}
