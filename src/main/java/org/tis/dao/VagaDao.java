package org.tis.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.tis.model.Empresa;
import org.tis.model.Vaga;

public class VagaDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager manager;
	
	
	public void salvar(Vaga vaga){
		manager.persist(vaga);
	}
	
	public Vaga merge(Vaga vaga) {
        vaga = manager.merge(vaga);
		return vaga;
	}

		
	public List<Vaga> vagasAbertasPorEmpresa(int id){
		return manager.createQuery("select v from Vaga v where v.empresa.id = :id and v.status = 'ABERTA' and v.dataFinalizacao >= current_date",Vaga.class).setParameter("id", id).getResultList();
	}

	public List<Vaga> vagasAbertasPessoa(String cargo, String departamento, String descricao, String nivel){
		return manager.createQuery("select v from Vaga v where v.status = 'ABERTA' "
				+ "and v.dataFinalizacao >= current_date "
				+ "and v.cargo LIKE :cargo "
				+ "and v.departamento LIKE :departamento "
				+ "and v.descricao LIKE :descricao "
				+ "and v.nivelEscolaridade LIKE :nivel",Vaga.class).
				setParameter("cargo", "%" + cargo + "%").
				setParameter("nivel", "%" + nivel + "%").
				setParameter("departamento", "%" + departamento + "%").
				setParameter("descricao", "%" + descricao + "%").
				getResultList();
	}
	
	public List<Vaga> vagasAbertasPessoa(){
		return manager.createQuery("select v from Vaga v where v.status = 'ABERTA' and v.dataFinalizacao >= current_date",Vaga.class).getResultList();
	}
	
	public Vaga findVagaById(int id){
		return manager.createQuery("select v from Vaga v where v.id = :id",Vaga.class).setParameter("id", id).getSingleResult();
	}

	public List<Vaga> vagasFinalizadasPorEmpresa(Integer id) {
		return manager.createQuery("select v from Vaga v where v.empresa.id = :id and v.status = 'FINALIZADA'",Vaga.class).setParameter("id", id).getResultList();
	}
	
	public List<Vaga> vagasVencidasPorEmpresa(Integer id) {
		return manager.createQuery("select v from Vaga v where v.empresa.id = :id and v.dataFinalizacao < current_date",Vaga.class).setParameter("id", id).getResultList();
	}

	public List<Vaga> CandidatosPorVaga(Empresa empresa) {
		return manager.createQuery("select distinct(v) from Vaga v join fetch v.candidatos where v.empresa = :empresa",Vaga.class).setParameter("empresa", empresa).getResultList();
	}

	
	
}
