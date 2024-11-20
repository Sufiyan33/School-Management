package com.sufiyan.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sufiyan.entities.User;
import com.sufiyan.repositories.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	// Write logic to get user from DB.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepo.findFirstByEmail(email);
		if(optionalUser.isEmpty())
			throw new UsernameNotFoundException("username not found", null);
		
		return new org.springframework.security.core.userdetails.User(
					optionalUser.get().getEmail(), 
					optionalUser.get().getPassword(), 
					new ArrayList<>());
	}
}
