package org.tis.bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.tis.dao.VagaDao;
import org.tis.model.Pessoa;
import org.tis.model.Vaga;

@Model
public class VagaBean{

	private Vaga vaga = new Vaga();
	private int idVaga;
	private String filtroCargo;
	private String filtroNivel;
	private String filtroDescricao;
	private String filtroDepartamento;
	
	
	@Inject
	private VagaDao vagaDao;
	
	@Inject
	private LoginEmpresaBean loginEmpresaBean;
	
	@Inject
	private LoginPessoaBean loginPessoaBean;
	
	@Transactional
	public String salvar(){
		//vaga.setFaixaSalarioMin(vaga.getFaixaSalarioMin().replace(".", "").replace(",", "."));
		vaga.setEmpresa(loginEmpresaBean.getEmpresa());
		vaga.setDataCriacao(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		vaga.setStatus("ABERTA");
		vagaDao.salvar(vaga);
		return "/empresa/lista-vagas-abertas?persisted=true&faces-redirect=true";
	}
	
	@Transactional
	public String editar() {
        vaga.setEmpresa(loginEmpresaBean.getEmpresa());
        vagaDao.merge(vaga);
		return "/empresa/lista-vagas-abertas?faces-redirect=true";
	}
	
	@Transactional
	public String voltar() {
		return "/pessoa/lista-vagas-pessoa?faces-redirect=true";
	}
	
	@Transactional
	public String finalizar(int id){
		this.vaga = vagaDao.findVagaById(id);
		this.vaga.setStatus("FINALIZADA");
		vagaDao.merge(vaga);
		return "/empresa/lista-vagas-abertas?finalized=true&faces-redirect=true";
	}
	
	@Transactional
	public String candidatar(int id){
		Pessoa candidato = new Pessoa();
		candidato = this.getLoginPessoaBean().getPessoa();
		this.vaga = vagaDao.findVagaById(id);
		this.vaga.getCandidatos().add(candidato);
		vagaDao.merge(vaga);
		return "/pessoa/lista-vagas-pessoa?candidatar=true&faces-redirect=true";
	}
	
	@Transactional
	public String verificaCandidato(int id){
		Pessoa candidato = new Pessoa();
		candidato = this.getLoginPessoaBean().getPessoa();
		this.vaga = vagaDao.findVagaById(id);
		if(this.vaga.getCandidatos().contains(candidato)){
			return "Já candidatou";
		}else{
			return "Candidatar";
		}
	}
	
	@Transactional
	public List<Vaga> getVagasAbertasPorEmpresa(){
		//Empresa empresa = new Empresa();
		//empresa = loginEmpresaBean.getEmpresa();
		return vagaDao.vagasAbertasPorEmpresa(loginEmpresaBean.getEmpresa().getId());
	}
	
	@Transactional
	public List<Vaga> getVagasAbertasPessoa(){
		if(getFiltroCargo() == null)this.setFiltroCargo("");
		if(getFiltroDepartamento() == null)this.setFiltroDepartamento("");
		if(getFiltroDescricao() == null)this.setFiltroDescricao("");
		if(getFiltroNivel() == null)this.setFiltroNivel("");
		return vagaDao.vagasAbertasPessoa(getFiltroCargo(), getFiltroDepartamento(), getFiltroDescricao(), getFiltroNivel());
	}
	
	@Transactional
	public List<Vaga> getCandidatosPorVaga(){
		return vagaDao.CandidatosPorVaga(this.getLoginEmpresaBean().getEmpresa());
	}
	
	@Transactional
	public List<Vaga> getVagasFinalizadasPorEmpresa(){
		//Empresa empresa = new Empresa();
		//empresa = loginEmpresaBean.getEmpresa();
		return vagaDao.vagasFinalizadasPorEmpresa(loginEmpresaBean.getEmpresa().getId());
	}
	
	@Transactional
	public List<Vaga> getVagasVencidasPorEmpresa(){
		return vagaDao.vagasVencidasPorEmpresa(loginEmpresaBean.getEmpresa().getId());
	}
	
	@Transactional
	public String detalhaVaga(int id){
		//this.vaga = vagaDao.findVagaById(id);
		//System.out.println("Id informado :::::::: " + id);
		return "/empresa/detalhe-vaga?faces-redirect=true&idVaga=" + id;
	}
	
	@Transactional
	public String detalhaVagaPessoa(int id){
		//this.vaga = vagaDao.findVagaById(id);
		//System.out.println("Id informado :::::::: " + id);
		return "/pessoa/detalhe-vaga-pessoa?faces-redirect=true&idVaga=" + id;
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

	public String getFiltroCargo() {
		return filtroCargo;
	}

	public void setFiltroCargo(String filtroCargo) {
		this.filtroCargo = filtroCargo;
	}

	public String getFiltroNivel() {
		return filtroNivel;
	}

	public void setFiltroNivel(String filtroNivel) {
		this.filtroNivel = filtroNivel;
	}

	public String getFiltroDescricao() {
		return filtroDescricao;
	}

	public void setFiltroDescricao(String filtroDescricao) {
		this.filtroDescricao = filtroDescricao;
	}

	public String getFiltroDepartamento() {
		return filtroDepartamento;
	}

	public void setFiltroDepartamento(String filtroDepartamento) {
		this.filtroDepartamento = filtroDepartamento;
	}

	public LoginPessoaBean getLoginPessoaBean() {
		return loginPessoaBean;
	}

	public void setLoginPessoaBean(LoginPessoaBean loginPessoaBean) {
		this.loginPessoaBean = loginPessoaBean;
	}
	

}
