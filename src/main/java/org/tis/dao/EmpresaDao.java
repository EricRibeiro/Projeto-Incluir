package org.tis.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.tis.model.Empresa;

public class EmpresaDao implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager manager;

    public void salvar(Empresa empresa) {
        manager.persist(empresa);
    }

    public void merge(Empresa empresa) {
        manager.merge(empresa);
    }

    public List<Empresa> listar() {
        return manager.createQuery("select e from Empresa e", Empresa.class).getResultList();
    }

    public Empresa buscaEmpresa(String login, String senha) {
        Empresa empresa = new Empresa();
        try {
            empresa = manager.createQuery("select e from Empresa e where e.login = :login", Empresa.class).setParameter("login", login).getSingleResult();
            if (empresa.getSenha().equals(senha)) {
                return empresa;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Empresa getEmpresaById(Integer id) {
        return manager.createQuery("select e from Empresa e where e.id = :id", Empresa.class).setParameter("id", id).getSingleResult();
    }

}
