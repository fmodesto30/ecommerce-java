package com.management.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.management.domain.config.UserInfo;
import com.management.domain.dao.UserDAO;
import com.management.domain.dto.AuthRequest;
import com.management.domain.model.UserEntity;

@Component
public class UserService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private UserEntity userEntity;

	@Override
	public UserDetails loadUserByUsername(String username) {

		Optional<UserEntity> user;
		try {
			user = userDAO.findByName(username);
			return user.map(UserInfo::new)
					.orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
	}

	public Optional<UserEntity> addNewUser(AuthRequest authRequest) throws Exception {

		Optional<UserEntity> user = userDAO.findByName(authRequest.getUsername().toUpperCase());
		
		if (user.isEmpty()) {

			userEntity = new UserEntity();
			userEntity.setName(authRequest.getUsername().toUpperCase());
			userEntity.setRole("ROLE_ADMIN");
			userEntity.setPassword(passwordEncoder.encode(authRequest.getPassword().toUpperCase()));
			userEntity.setEmail(authRequest.getEmail().toLowerCase());
			return userDAO.addUser(userEntity);
		}
		
		return Optional.empty();
	}
}