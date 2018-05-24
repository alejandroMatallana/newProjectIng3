package com.cooperativacafetera.dealmanager.implementacion;


import javax.annotation.PostConstruct;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cooperativacafetera.dealmanager.dao.DAOGenerico;
import com.cooperativacafetera.dealmanager.excepciones.ExcepcionNegocio;


public abstract class EJBGenerico<T> {

	@PersistenceContext
	protected EntityManager em;

	protected DAOGenerico dao;

	@PostConstruct
	public void inicializar()  {
		dao = new DAOGenerico(getEm(), getClase());
	}

	

	public void editar(T entidad) throws ExcepcionNegocio {
		dao.editar(entidad);
	}

	public T buscar(Object pk) {
		return dao.buscar(pk);
	}

	public void eliminar(T entidad) throws ExcepcionNegocio {
		dao.eliminar(entidad);
	}

	public void crear(T entidad) throws ExcepcionNegocio {
			dao.crear(entidad);
	}

	public abstract Class getClase();

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	//k

}
