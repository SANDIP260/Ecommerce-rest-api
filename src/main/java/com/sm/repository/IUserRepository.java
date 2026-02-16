package com.sm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String mail);
}
