package com.hailmik.jwtattempt2.service;

import com.hailmik.jwtattempt2.model.AuthenticationResponse;
import com.hailmik.jwtattempt2.model.JwtAppUser;
import com.hailmik.jwtattempt2.repo.JwtAppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final JwtAppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(JwtAppUser request) {
		JwtAppUser user = new JwtAppUser();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setRole(request.getRole());

		user = appUserRepository.save(user);

		String token = jwtService.generateToken(user);

		return new AuthenticationResponse(token);
	}

	public AuthenticationResponse authenticate(JwtAppUser request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getUsername(),
				request.getPassword()
			)
		);

		JwtAppUser user = appUserRepository.findJwtAppUserByUsername(request.getUsername())
			.orElseThrow();

		String token = jwtService.generateToken(user);

		return new AuthenticationResponse(token);
	}
}
