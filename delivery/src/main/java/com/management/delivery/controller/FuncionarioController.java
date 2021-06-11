package com.management.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.domain.exception.RecordNotFoundException;
import com.management.domain.model.FuncionarioEntity;
import com.management.domain.service.FuncionarioService;


@RestController
@RequestMapping("/funcionario")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FuncionarioController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping
	public ResponseEntity<List<FuncionarioEntity>> getAllEmployees() throws RecordNotFoundException, Exception {
		
		List<FuncionarioEntity> funcionarioList = funcionarioService.listAll();
		return new ResponseEntity<List<FuncionarioEntity>>(funcionarioList, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioEntity> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException, Exception {
		
		
		FuncionarioEntity funcionarioEntity = funcionarioService.findById(id);
		
		return new ResponseEntity<FuncionarioEntity>(funcionarioEntity, HttpStatus.OK);
	}
	
}
