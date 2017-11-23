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
        if (pessoaDao.nmDeUsuarioEstaDisponivel(pessoa.getLogin())) {
            pessoaDao.salvar(pessoa);
            return "/pessoa/login-pessoa?persisted=true&faces-redirect=true";
        } else {
            return "/pessoa/cadastra-pessoa?usuario-disponivel=false&faces-redirect=true";
        }
	}
	
	@Transactional
	public String editar(){
        pessoaDao.merge(pessoa);
		return "/pessoa/detalhe-pessoa?faces-redirect=true";
	}
	
	@Transactional
	public Pessoa getPessoaById(Integer id){
		this.pessoa = pessoaDao.getPessoaById(id);
		return this.pessoa;
	}
	
	

	public Pessoa getPessoa() {
		return pessoa;
	}

}
