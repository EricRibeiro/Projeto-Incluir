package org.tis.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tis.model.Pessoa;

public class PessoaDao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Pessoa pessoa){
		manager.persist(pessoa);
	}
	
	public Pessoa merge(Pessoa pessoa) {
        pessoa = manager.merge(pessoa);
		return pessoa;
	}
		
	
	public List<Pessoa> listar(){
		return manager.createQuery("select e from Pessoa e", Pessoa.class).getResultList();
	}

	public Pessoa buscaPessoa(String login, String senha) {
		Pessoa pessoa = new Pessoa();
		try {
		
			pessoa = manager.createQuery("select e from Pessoa e where e.login = :login", Pessoa.class).setParameter("login", login).getSingleResult();
		
			if(pessoa.getSenha().equals(senha)){
			
				return pessoa;
		
			}else{
			
				return null;
		
			}
		} catch ( Exception e ) {
			
			return null;
			
		}
	}//end buscaPessoa()

	public Pessoa getPessoaById(int id) {
		return manager.createQuery("select e from Pessoa e where e.id = :id", Pessoa.class).setParameter("id", id).getSingleResult();
	}

}
