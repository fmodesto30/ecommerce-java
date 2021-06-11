package com.management.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.management.domain.model.FuncionarioEntity;

@Service
public interface FuncionarioService {

	public List<FuncionarioEntity> listAll() throws Exception;
	
	public FuncionarioEntity findById(long id) throws Exception;

}
