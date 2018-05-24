package com.cooperativacafetera.dealmanager.ejb;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.cooperativacafetera.dealmanager.implementacion.EJBGenerico;
import com.cooperativacafetera.dealmanager.modelo.Deuda;


@Stateless
@LocalBean
public class DeudaEJB extends EJBGenerico<Deuda> {

	@Override
	public Class getClase() {
		// TODO Auto-generated method stub
		return Deuda.class;
	}
		

	/**
	 * crea una deuda
	 */
	public void crear(Deuda deuda) {
		dao.crear(deuda);
	}
	
	


	/**
	 * busca una deuda
	 * @param codigo
	 * el codigo de la deuda a buscar
	 * @return la deuda si la encuentra
	 */
	public Deuda buscar(Object codigo) {
		return dao.buscar(codigo);
	}
	
	
	
}