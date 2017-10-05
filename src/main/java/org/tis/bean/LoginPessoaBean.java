package org.tis.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.tis.dao.PessoaDao;
import org.tis.model.Pessoa;

@Named
@SessionScoped
public class LoginPessoaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa = new Pessoa();
	private String login;
	private String senha;
	
	@Inject
	private PessoaDao pessoaDao;
	
	@Transactional
	public String login(){
		if(pessoaDao.buscaPessoa(login, senha).equals(null)){
			System.out.println("Nulo.");
			return "/pessoa/login-pessoa?faces-redirect=true";
		}else{
			this.pessoa = pessoaDao.buscaPessoa(login, senha);
			//System.out.println("Pessoa: " + pessoa.getUsuario());
			return "/pessoa/admin-pessoa?faces-redirect=true";
		}
	}
	
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
