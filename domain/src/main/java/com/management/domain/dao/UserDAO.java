package com.management.domain.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.management.domain.model.UserEntity;

@Component
public interface UserDAO{

	public Optional<UserEntity> findByName(String username) throws Exception;

	public Optional<UserEntity> addUser(UserEntity userEntity) throws Exception;
}
