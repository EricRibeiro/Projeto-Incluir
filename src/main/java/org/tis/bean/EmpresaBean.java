package org.tis.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.tis.dao.EmpresaDao;
import org.tis.model.Empresa;

@Model
public class EmpresaBean {
	
	private Empresa empresa = new Empresa();
	
	@Inject
	private EmpresaDao empresaDao;
	
	@Transactional
	public String salvar(){
		empresaDao.salvar(empresa);
		return "/empresa/login-empresa?faces-redirect=true";
	}
	
	public void editar(){
		
	}
	
	@Transactional
	public Empresa getEmpresaById(Integer id){
		return empresaDao.getEmpresaById(id);
	}
	
	

	public Empresa getEmpresa() {
		return empresa;
	}

}
