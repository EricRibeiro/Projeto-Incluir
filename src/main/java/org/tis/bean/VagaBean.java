package org.tis.bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.tis.dao.VagaDao;
import org.tis.model.Vaga;

@Model
public class VagaBean{

	private Vaga vaga = new Vaga();
	private int idVaga;
	
	
	@Inject
	private VagaDao vagaDao;
	
	@Inject
	private LoginEmpresaBean loginEmpresaBean;
	
	@Transactional
	public String salvar(){
		vaga.setEmpresa(loginEmpresaBean.getEmpresa());
		vaga.setDataCriacao(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		vaga.setStatus("ABERTA");
		vagaDao.salvar(vaga);
		return "/empresa/lista-vagas-abertas?faces-redirect=true";
	}
	
	@Transactional
	public String editar(int id){
		vaga.setId(id);
		vaga.setEmpresa(loginEmpresaBean.getEmpresa());
		vagaDao.merge(vaga);
		return "/empresa/lista-vagas-abertas?faces-redirect=true";
	}
	
	@Transactional
	public String finalizar(int id){
		this.vaga = vagaDao.findVagaById(id);
		this.vaga.setStatus("FINALIZADA");
		vagaDao.merge(vaga);
		
		return "/empresa/lista-vagas-abertas?faces-redirect=true";
	}
	
	@Transactional
	public List<Vaga> getVagasAbertasPorEmpresa(){
		//Empresa empresa = new Empresa();
		//empresa = loginEmpresaBean.getEmpresa();
		return vagaDao.vagasAbertasPorEmpresa(loginEmpresaBean.getEmpresa().getId());
	}
	
	@Transactional
	public List<Vaga> getVagasFinalizadasPorEmpresa(){
		//Empresa empresa = new Empresa();
		//empresa = loginEmpresaBean.getEmpresa();
		return vagaDao.vagasFinalizadasPorEmpresa(loginEmpresaBean.getEmpresa().getId());
	}
	
	@Transactional
	public String detalhaVaga(int id){
		//this.vaga = vagaDao.findVagaById(id);
		//System.out.println("Id informado :::::::: " + id);
		return "/empresa/detalhe-vaga?faces-redirect=true&idVaga=" + id;
	}
	
	public Vaga findVagaById(){
		this.vaga = vagaDao.findVagaById(idVaga);
		return this.vaga;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public VagaDao getVagaDao() {
		return vagaDao;
	}

	public void setVagaDao(VagaDao vagaDao) {
		this.vagaDao = vagaDao;
	}

	public LoginEmpresaBean getLoginEmpresaBean() {
		return loginEmpresaBean;
	}

	public void setLoginEmpresaBean(LoginEmpresaBean loginEmpresaBean) {
		this.loginEmpresaBean = loginEmpresaBean;
	}

	public int getIdVaga() {
		return idVaga;
	}

	public void setIdVaga(int idVaga) {
		this.idVaga = idVaga;
	}
	

}
