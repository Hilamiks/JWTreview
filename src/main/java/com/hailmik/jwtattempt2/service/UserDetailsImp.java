package com.hailmik.jwtattempt2.service;

import com.hailmik.jwtattempt2.repo.JwtAppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsImp implements UserDetailsService {
	private final JwtAppUserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findJwtAppUserByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
