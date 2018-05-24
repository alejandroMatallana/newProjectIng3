package com.cooperativacafetera.dealmanager.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.cooperativacafetera.dealmanager.implementacion.EJBGenerico;
import com.cooperativacafetera.dealmanager.modelo.Cliente;
import com.cooperativacafetera.dealmanager.modelo.Deuda;
import com.cooperativacafetera.dealmanager.modelo.PagoDeuda;

@LocalBean
@Stateless
public class PagoDeudaEJB extends EJBGenerico<PagoDeuda> {

	/**
	 * crea un pago para un deudor
	 */
	public void crear(PagoDeuda pagoDeuda) {
		dao.crear(pagoDeuda);
	}

	/**
	 * Crea un pago para una deuda de un cliente
	 * 
	 * @param pagoDeuda
	 *            el pago de la deuda a crear
	 * @return 0 si se puede registrar, 1 si el total a pagar es mayor que el de
	 *         la deuda, 2 si la deuda no corresponde al cliente
	 */
	public int crearPagoDeuda(PagoDeuda pagoDeuda) {
		Deuda deuda = pagoDeuda.getDeuda();
		Cliente cliente = pagoDeuda.getCliente();
		if (deuda.getCliente().getCedula().equals(cliente.getCedula())) {
			List<PagoDeuda> pagos = listarPagos(cliente, deuda);
			double total = pagoDeuda.getValor();
			for (int i = 0; i < pagos.size(); i++) {
				total = total + pagos.get(i).getValor();
			}
			if (total > deuda.getValor()) {
				return 1;
			} else {
				return 0;
			}
		}
		return 2;
	}

	/**
	 * lista los pagos de un deudor
	 * 
	 * @param cliente
	 *            el cliente del pago
	 * @param deuda
	 *            la deuda del pago
	 * @return una lista de pagos de un cliente en una deuda
	 */
	public List<PagoDeuda> listarPagos(Cliente cliente, Deuda deuda) {
		return dao.ejecutarNamedQuery(PagoDeuda.LISTAR_PAGOS_CLIENTES, cliente, deuda);
	}

	@Override
	public Class getClase() {
		return PagoDeuda.class;
	}
}
