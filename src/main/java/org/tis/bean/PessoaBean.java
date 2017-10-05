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
	
	@Transactional
	public String salvar(){
		pessoaDao.salvar(pessoa);
		return "/pessoa/login-pessoa?faces-redirect=true";
	}
	
	public void editar(){
		
	}
	
	@Transactional
	public Pessoa getPessoaById(Integer id){
		return pessoaDao.getPessoaById(id);
	}
	
	

	public Pessoa getPessoa() {
		return pessoa;
	}

}
