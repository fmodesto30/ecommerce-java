package com.management.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.management.domain.model.FuncionarioEntity;

@Service
public interface FuncionarioService {

	public List<FuncionarioEntity> listAll() throws Exception;
	
//	public FuncionarioEntity findById(long id) throws Exception;
	
	Optional<FuncionarioEntity> findById(long id) throws Exception;

}
