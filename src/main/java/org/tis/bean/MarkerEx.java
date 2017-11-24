package org.tis.bean;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

public class MarkerEx extends Marker {
	
	private static final long serialVersionUID = 1L;
	
	private String razaoSocial;
	private String vagas;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	
	public MarkerEx(LatLng latlng, String title) {
		super(latlng, title);
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

}
