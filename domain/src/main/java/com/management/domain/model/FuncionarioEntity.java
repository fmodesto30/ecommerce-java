package com.management.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_FUNCIONARIOS")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="primeiro_nome", nullable=false, length=100)
    private String primeiroNome;
    
    @Column(name="ultimo_nome", nullable=false, length=100)
    private String ultimoNome;
    
    @Column(name="rg", nullable=false, length=15)
    private String rg;
    
    @Column(name="cpf", nullable=false, length=15)
    private String cpf;
    
    @Column(name="telefone", nullable=true, length=10)
    private String telefone; 

    @Column(name="email", nullable=true, length=100)
    private String email;
    
    @Column(name="data", nullable=false)
    private String dateS;
    
    public FuncionarioEntity() {
    	
	}
    
    //Getters and Setters

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateS() {
		return dateS;
	}

	public void setDateS(String dateS) {
		this.dateS = dateS;
	}

	@Override
	public String toString() {
		return "FuncionarioEntity [" + (id != null ? "id=" + id + ", " : "")
				+ (primeiroNome != null ? "primeiroNome=" + primeiroNome + ", " : "")
				+ (ultimoNome != null ? "ultimoNome=" + ultimoNome + ", " : "") + (rg != null ? "rg=" + rg + ", " : "")
				+ (cpf != null ? "cpf=" + cpf + ", " : "") + (telefone != null ? "telefone=" + telefone + ", " : "")
				+ (email != null ? "email=" + email + ", " : "") + (dateS != null ? "dateS=" + dateS : "") + "]";
	}
	
	
}