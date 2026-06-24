package com.lmsportal.controllers;

import com.lmsportal.dto.JwtResponse;
import com.lmsportal.dto.LoginRequest;
import com.lmsportal.dto.SignupRequest;
import com.lmsportal.models.User;
import com.lmsportal.repositories.UserRepository;
import com.lmsportal.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


private final AuthService authService;
private final UserRepository userRepository;


public AuthController(AuthService authService, UserRepository userRepository) {
this.authService = authService;
this.userRepository = userRepository;
}


@PostMapping("/signup")
public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req) {
String message = authService.signup(req);
return ResponseEntity.ok().body(java.util.Collections.singletonMap("message", message));
}


@PostMapping("/login")
public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req) {
JwtResponse jwt = authService.login(req);
return ResponseEntity.ok(jwt);
}


@GetMapping("/profile")
public ResponseEntity<?> profile() {
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
if (auth == null || !auth.isAuthenticated()) {
return ResponseEntity.status(401).build();
}
String email = auth.getName();
User user = userRepository.findByEmail(email).orElse(null);
if (user == null) return ResponseEntity.notFound().build();
return ResponseEntity.ok(user);
}
}