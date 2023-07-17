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
import com.management.domain.model.UserEntity;

@Component
public class UserService implements  UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

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
	
	public Optional<UserEntity> addNewUser(UserEntity userEntity) throws Exception {
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		return userDAO.addUser(userEntity);
	}
}