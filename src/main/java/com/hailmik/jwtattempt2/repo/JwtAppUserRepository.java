package com.hailmik.jwtattempt2.repo;

import com.hailmik.jwtattempt2.model.JwtAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtAppUserRepository extends JpaRepository<JwtAppUser, Long> {
	Optional<JwtAppUser> findJwtAppUserByUsername(String username);
	Optional<JwtAppUser> findJwtAppUserByEmail(String email);
}
