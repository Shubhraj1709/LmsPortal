package com.lmsportal.services;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lmsportal.config.JwtProvider;
import com.lmsportal.dto.JwtResponse;
import com.lmsportal.dto.LoginRequest;
import com.lmsportal.dto.SignupRequest;
import com.lmsportal.enums.RoleEnum;
import com.lmsportal.models.User;
import com.lmsportal.repositories.UserRepository;


@Service
public class AuthService {


private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final JwtProvider jwtProvider;
private final AuthenticationManager authenticationManager;


public AuthService(UserRepository userRepository,
PasswordEncoder passwordEncoder,
JwtProvider jwtProvider,
AuthenticationManager authenticationManager) {
this.userRepository = userRepository;
this.passwordEncoder = passwordEncoder;
this.jwtProvider = jwtProvider;
this.authenticationManager = authenticationManager;
}


	public String signup(SignupRequest req) {
	
			if (userRepository.existsByEmail(req.getEmail())) {
				throw new RuntimeException("Email already in use");
				}
			
			
			RoleEnum role = RoleEnum.STUDENT;
			if (req.getRole() != null) {
				try {
				role = RoleEnum.valueOf(req.getRole().toUpperCase());
				} catch (Exception ignored) {
				role = RoleEnum.STUDENT;
				}
			}
			
			
			User user = User.builder()
			.name(req.getName())
			.email(req.getEmail())
			.password(passwordEncoder.encode(req.getPassword()))
			.role(role)
			.build();
			
			
			userRepository.save(user);
			return "User registered successfully";
}


public JwtResponse login(LoginRequest req) {
	Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
			);
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String token = jwtProvider.generateToken(authentication);
	org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
	// principal.getUsername() is email
	User user = userRepository.findByEmail(principal.getUsername()).orElseThrow();
	return new JwtResponse(token, user);
	}

public Long getLoggedInUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication == null || !authentication.isAuthenticated()) {
        throw new RuntimeException("User not authenticated");
    }

    // Spring returns org.springframework.security.core.userdetails.User
    org.springframework.security.core.userdetails.User principal =
            (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

    String email = principal.getUsername();

    // Load full User entity from database
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return user.getId();
}

}