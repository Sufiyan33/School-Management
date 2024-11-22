package com.sufiyan.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sufiyan.dto.AuthenticationRequest;
import com.sufiyan.dto.AuthenticationResponse;
import com.sufiyan.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationControllers {

	@Autowired
	private AuthenticationManager authentioncationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/authenticate")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest auth, HttpServletResponse response) throws IOException {
		try {
			System.out.println("Authenticating email: " + auth.getEmail());
			authentioncationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}catch(DisabledException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created");
			return null;
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getEmail());
		
		// made code changes here..
		System.out.println("Loaded UserDetails: " + userDetails);
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		 System.out.println("Generated JWT: " + jwt);
		
		return new AuthenticationResponse(jwt); 
	}
}
