package com.mandiwal.security;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.mandiwal.persistence.dao.UserRepository;
import com.mandiwal.persistence.model.User;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String execute(Connection<?> connection) {
		System.out.println("signup === ");
		UserProfile userProfile = connection.fetchUserProfile();
		String id = null == userProfile.getId() ? randomAlphabetic(8) : userProfile.getId();
		User user = userRepository.findByUsername(id);
		if (null == user) {
			user = new User();
			user.setUsername(id);
		}
		user.setPassword(connection.getDisplayName());
		user.setImageUrl(connection.getImageUrl());
		userRepository.save(user);
		return user.getUsername();
	}

}
