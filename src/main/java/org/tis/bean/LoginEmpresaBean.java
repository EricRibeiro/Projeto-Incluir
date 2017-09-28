package org.tis.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.tis.dao.EmpresaDao;
import org.tis.model.Empresa;

@Named
@SessionScoped
public class LoginEmpresaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Empresa empresa = new Empresa();
	private String login;
	private String senha;
	
	@Inject
	private EmpresaDao empresaDao;
	
	@Transactional
	public String login(){
		if(empresaDao.buscaEmpresa(login, senha).equals(null)){
			System.out.println("Nulo.");
			return "/empresa/login-empresa?faces-redirect=true";
		}else{
			this.empresa = empresaDao.buscaEmpresa(login, senha);
			System.out.println("Empresa: " + empresa.getRazaoSocial());
			return "/empresa/admin-empresa?faces-redirect=true";
		}
	}
	
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
