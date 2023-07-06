package com.management.domain.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.management.domain.model.FuncionarioEntity;

@Component
public interface FuncionarioDAO {

	public List<FuncionarioEntity> listAll() throws Exception;
	
	Optional<FuncionarioEntity> findById(long id) throws Exception;

}
