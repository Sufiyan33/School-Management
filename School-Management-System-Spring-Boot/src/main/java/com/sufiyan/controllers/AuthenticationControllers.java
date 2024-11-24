package com.sufiyan.controllers;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
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
import com.sufiyan.entities.User;
import com.sufiyan.repositories.UserRepository;
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
	
	@Autowired
	private UserRepository userRepo;
	
	private static final String TOKEN_PREFIX = "Bearer ";
	private static final String HEADER_STRING = "Authorization ";
	
	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest auth, HttpServletResponse response) throws IOException, JSONException {
		try {
			System.out.println("Authenticating email: " + auth.getEmail());
			authentioncationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}catch(DisabledException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created");
			return;
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getEmail());
		
		Optional<User> userOpt = userRepo.findFirstByEmail(userDetails.getUsername());
		
		System.out.println("Loaded UserDetails: " + userDetails);
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		
		if(userOpt.isPresent()) {
			response.getWriter().write(new JSONObject()
					.put("userId", userOpt.get().getId())
					.put("role", userOpt.get().getRole())
					.toString());
		}
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, X-Pingother, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
		response.setHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
	}
}
