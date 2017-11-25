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

        if (empresaDao.nmDeUsuarioEstaDisponivel(empresa.getLogin())) {
            empresaDao.salvar(empresa);
            return "/empresa/login-empresa?persisted=true&faces-redirect=true";
        } else {
            return "/empresa/cadastra-empresa?usuario-disponivel=false&faces-redirect=true";
        }
	}
	
	@Transactional
	public String editar(int id){
		empresa.setId(id);
		empresaDao.merge(empresa);
		return "/empresa/detalhe-empresa?faces-redirect=true";
	}
	
	@Transactional
	public Empresa getEmpresaById(Integer id){
		this.empresa = empresaDao.getEmpresaById(id);
		return this.empresa;
	}
	
	

	public Empresa getEmpresa() {
		return empresa;
	}

}
