package com.hailmik.jwtattempt2.controller;

import com.hailmik.jwtattempt2.model.AuthenticationResponse;
import com.hailmik.jwtattempt2.model.JwtAppUser;
import com.hailmik.jwtattempt2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
		@RequestBody JwtAppUser request
	) {
		return ResponseEntity.ok(authService.register(request));
	}
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
		@RequestBody JwtAppUser request
	) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

	@GetMapping("/user_endpoints/hi")
	public ResponseEntity<String> hi() {
		return ResponseEntity.ok("hi to all users");
	}

	@GetMapping("/admin_endpoints/hi")
	public ResponseEntity<String> secretHi() {
		return ResponseEntity.ok("secret hi to all admins");
	}
}
