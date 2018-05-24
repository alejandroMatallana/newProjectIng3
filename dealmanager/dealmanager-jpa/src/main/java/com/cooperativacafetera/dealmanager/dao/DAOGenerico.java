package com.cooperativacafetera.dealmanager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAOGenerico {

	private EntityManager em;
	private Class clase;

	public DAOGenerico(EntityManager em, Class clase) {
		super();
		this.em = em;
		this.clase = clase;
	}

	public void crear(Object obj) {
		em.persist(obj);
	}

	public void editar(Object obj) {
		em.merge(obj);
	}

	public void eliminar(Object obj) {
		em.remove(em.contains(obj) ? obj : em.merge(obj));
	}

	public <T> T buscar(Object pk) {

		return (T) em.find(clase, pk);
	}

	public <T> List<T> ejecutarNamedQuery(String nombre, Object... parametros) {
		Query q = em.createNamedQuery(nombre);
		for (int i = 0; i < parametros.length; i++) {
			Object param = parametros[i];
			q.setParameter(i + 1, param);
		}

		return q.getResultList();
	}

	public void refrescar(Object entity) {

		em.refresh(entity);
	}
	
	
	

}
