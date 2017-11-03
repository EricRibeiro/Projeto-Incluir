package org.tis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Vaga implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String cargo;
	private String departamento;
	private String nivelEscolaridade;
	private String descricao;
    private String status;

    @Temporal(TemporalType.DATE)
	private Date dataCriacao;
	@Temporal(TemporalType.DATE)
	private Date dataFinalizacao;
	private String faixaSalarioMin;
	private String faixaSalarioMax;
	@ManyToOne
	private Empresa empresa;
	@ManyToMany
	private List<Pessoa> candidatos = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getNivelEscolaridade() {
		return nivelEscolaridade;
	}
	public void setNivelEscolaridade(String nivelEscolaridade) {
		this.nivelEscolaridade = nivelEscolaridade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getFaixaSalarioMin() {
		return faixaSalarioMin;
	}
	public void setFaixaSalarioMin(String faixaSalarioMin) {
		this.faixaSalarioMin = faixaSalarioMin;
	}
	public String getFaixaSalarioMax() {
		return faixaSalarioMax;
	}
	public void setFaixaSalarioMax(String faixaSalarioMax) {
		this.faixaSalarioMax = faixaSalarioMax;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Pessoa> getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(List<Pessoa> candidatos) {
		this.candidatos = candidatos;
	}
	
}
