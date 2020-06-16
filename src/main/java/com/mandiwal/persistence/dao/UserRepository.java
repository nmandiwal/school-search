package com.mandiwal.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mandiwal.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(final String username);

	List<User> findByUsernameIn(List<String> userNames);

}
