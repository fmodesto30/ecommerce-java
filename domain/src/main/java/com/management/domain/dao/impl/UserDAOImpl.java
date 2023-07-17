package com.management.domain.dao.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.management.domain.dao.UserDAO;
import com.management.domain.model.UserEntity;

@Component
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public Optional<UserEntity> findByName(String username) throws Exception {
		
		final StringBuffer sql = new StringBuffer();
		sql.append("\n SELECT * ");
		sql.append("\n FROM TB_USER");
		sql.append("\n WHERE NAME = ? ");
		
		Object[] args = { username };
		UserEntity userEntity;
		
		try {
			userEntity = (UserEntity) jdbcTemplate.queryForObject(sql.toString(), args, new BeanPropertyRowMapper(UserEntity.class));
			return Optional.of(userEntity);
		} catch (EmptyResultDataAccessException ex) {
			// Handle the case when no matching record is found
			return Optional.empty();
		}
	}

	@Override
	public Optional<UserEntity> addUser(UserEntity userEntity) throws Exception {
		
		final StringBuffer sql = new StringBuffer();
		sql.append("\n INSERT INTO   ");
		sql.append("\n TB_USER (	 ");
		sql.append("\n NAME,         ");
		sql.append("\n EMAIL,        ");
		sql.append("\n PASSWORD,     ");
		sql.append("\n ROLES,        ");
		sql.append("\n TIME         )");
		sql.append("\n VALUES ( ?, ?, ?, ?, ?)");
		
		Object[] args = { userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRoles(), new Date() };
		
		int rowsAffected = jdbcTemplate.update(sql.toString(), args);
		if (rowsAffected == 0) {
			throw new Exception("Failed to save product.");
		}

		return Optional.of(userEntity);
	}
	
	
}
