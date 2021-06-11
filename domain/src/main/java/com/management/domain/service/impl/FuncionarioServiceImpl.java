package com.management.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.domain.dao.FuncionarioDAO;
import com.management.domain.exception.RecordNotFoundException;
import com.management.domain.model.FuncionarioEntity;
import com.management.domain.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	FuncionarioDAO funcionarioDAO;

	@Override
	public List<FuncionarioEntity> listAll() throws Exception {

		List<FuncionarioEntity> funcionarioList = funcionarioDAO.listAll();

		if (!funcionarioList.isEmpty())
			return funcionarioList;		
		else
            throw new RecordNotFoundException("Não existe funcionário(s) !");
	}
	
	public FuncionarioEntity findById(long id) throws Exception{
		
		FuncionarioEntity funcionarioEntity = funcionarioDAO.findById(id);
		
		if(funcionarioEntity != null && !funcionarioEntity.equals(null))
			return funcionarioEntity;
		else
            throw new RecordNotFoundException("Esse funcionário(a) não existe !");
	}

}
