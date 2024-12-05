package com.sufiyan.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sufiyan.filters.JwtRequestFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfiguration {

	private final JwtRequestFilter jwtRequestFilter;
	
	public WebSecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}
	
	// Let's authorize our endpoinds
	@Bean
	public SecurityFilterChain sucSecurityFilterChain(HttpSecurity http) throws Exception {
		
		/*http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/authenticate").permitAll()
				.anyRequest().authenticated())
				.sessionManagement(
						session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();*/
				
				
		return http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/authenticate")
		.permitAll()
		.and()
		.authorizeHttpRequests()
		.requestMatchers("/api/**")
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
		.build();
	} 
	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
