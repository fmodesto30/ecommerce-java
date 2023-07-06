package com.management.domain.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.management.domain.dao.FuncionarioDAO;
import com.management.domain.exception.RecordNotFoundException;
import com.management.domain.model.FuncionarioEntity;


@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class FuncionarioDAOImpl implements FuncionarioDAO{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<FuncionarioEntity> listAll() throws SQLException {

		final StringBuffer sql = new StringBuffer();

		sql.append("\n SELECT * ");
		sql.append("\n FROM TB_FUNCIONARIOS ");

		List<FuncionarioEntity> funcionarioList =  jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(FuncionarioEntity.class));
		return funcionarioList;
	}
	
//	public FuncionarioEntity findById(long id) throws SQLException {
//		
//		final StringBuffer sql = new StringBuffer();
//		sql.append("\n SELECT * ");
//		sql.append("\n FROM TB_FUNCIONARIOS ");
//		sql.append("\n WHERE id = ? ");
//		
//		Object[] args = {id};
//		FuncionarioEntity employeeEntity = (FuncionarioEntity) jdbcTemplate.queryForObject (sql.toString(), args, new BeanPropertyRowMapper(FuncionarioEntity.class));
//		return employeeEntity;
//	}

	@Override
	public Optional<FuncionarioEntity> findById(long id) throws Exception {
		
		final StringBuffer sql = new StringBuffer();
		sql.append("\n SELECT * ");
		sql.append("\n FROM TB_FUNCIONARIOS");
		sql.append("\n WHERE id = ? ");
		
		Object[] args = {id};
		FuncionarioEntity funcionarioEntity;
		
		try {
			funcionarioEntity = (FuncionarioEntity) jdbcTemplate.queryForObject (sql.toString(), args, new BeanPropertyRowMapper(FuncionarioEntity.class));
			return Optional.of(funcionarioEntity);
		} catch (Exception e) {
			throw new RecordNotFoundException("Não existe funcionário(s) !");
			// TODO: handle exception
		}
	}


}
