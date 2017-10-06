package org.tis.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.tis.dao.PessoaDao;
import org.tis.model.Pessoa;

@Model
public class PessoaBean {
	
	private Pessoa pessoa = new Pessoa();
	
	@Inject
	private PessoaDao pessoaDao;
	
	@Inject 
	private LoginPessoaBean loginPessoaBean;
	
	@Transactional
	public String salvar(){
		pessoaDao.salvar(pessoa);
		// TRATAR AS EXCECOES DE CADASTRADOS ERRADOS, JA EXISTENTE ETC
		return "/pessoa/login-pessoa?faces-redirect=true";
	}
	
	@Transactional
	public String editar(){
		pessoa.setPessoa(loginPessoaBean.getPessoa());
        pessoaDao.merge(pessoa);
		return "/pessoa/detalhe-pessoa?faces-redirect=true";
	}
	
	@Transactional
	public Pessoa getPessoaById(Integer id){
		return pessoaDao.getPessoaById(id);
	}
	
	

	public Pessoa getPessoa() {
		return pessoa;
	}

}
