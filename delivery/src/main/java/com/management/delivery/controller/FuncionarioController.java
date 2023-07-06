package com.management.delivery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.management.domain.exception.RecordNotFoundException;
import com.management.domain.model.FuncionarioEntity;
import com.management.domain.service.FuncionarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;


@RestController
@RequestMapping("/api")
@Api(value = "API REST funcionarios")
@CrossOrigin(origins = "*")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Executado com sucesso", response = String.class),
        @ApiResponse(code = 400, message = "Requisição inválida"),
        @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso, pois sua credencial foi recusada"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção não esperada") })
public class FuncionarioController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping("/funcionarios")
	@ApiOperation(
          value = "Retorna uma lista de funcionários",
		  notes = "Receber dados em formato ExemploDTO, salvar em banco de dados e devolver json com id.",
		  response = String.class,
		  authorizations = { @Authorization(value = "BasicAuth") })
	public ResponseEntity<List<FuncionarioEntity>> getAllEmployees() throws RecordNotFoundException, Exception {
		
		List<FuncionarioEntity> funcionarioList = funcionarioService.listAll();
		return new ResponseEntity<List<FuncionarioEntity>>(funcionarioList, HttpStatus.OK);
	}
	
	
	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<Optional<FuncionarioEntity>> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException, Exception {
		
		Optional<FuncionarioEntity> funcionarioEntity = funcionarioService.findById(id);
		return new ResponseEntity<Optional<FuncionarioEntity>>(funcionarioEntity, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Lista Funcionario com ResponseBody")
	@GetMapping("/funcionariosJPA/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Optional<FuncionarioEntity> getCarro() throws Exception{
		return funcionarioService.findById(1L);
	}
}
