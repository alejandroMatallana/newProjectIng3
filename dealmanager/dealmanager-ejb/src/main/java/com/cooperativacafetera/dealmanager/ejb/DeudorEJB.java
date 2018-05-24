package com.cooperativacafetera.dealmanager.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.cooperativacafetera.dealmanager.implementacion.EJBGenerico;
import com.cooperativacafetera.dealmanager.modelo.Cliente;


@LocalBean
@Stateless
public class DeudorEJB extends EJBGenerico<Cliente>{

	@Override
	public Class getClase() {
		// TODO Auto-generated method stub
		return Cliente.class;
	}

	/**
	 * crea un deudor
	 */
	public void crear(Cliente cliente) {
		dao.crear(cliente);
	}

	/**
	 * busca un cliente
	 * 
	 * @param cedula
	 *            la cedula del cliente a buscar
	 * @return el cliente si lo encuentra
	 */
	public Cliente buscar(Object cedula) {
		return dao.buscar(cedula);
	}

}
