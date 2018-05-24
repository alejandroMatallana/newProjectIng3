package com.cooperativacafetera.dealmanager.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.cooperativacafetera.dealmanager.implementacion.EJBGenerico;
import com.cooperativacafetera.dealmanager.modelo.Compromiso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.cooperativacafetera.dealmanager.dto.CompromisoDTO;
import com.cooperativacafetera.dealmanager.implementacion.EJBGenerico;
import com.cooperativacafetera.dealmanager.modelo.Cliente;
import com.cooperativacafetera.dealmanager.modelo.Compromiso;
import com.cooperativacafetera.dealmanager.modelo.Deuda;

@LocalBean
@Stateless
public class CompromisoEJB extends EJBGenerico<Compromiso> {

	int numero = 0;

	public CompromisoEJB() {
	}

	@Override
	public Class getClase() {
		// TODO Auto-generated method stub
		return Compromiso.class;
	}

	public void crear(Compromiso compromiso) {
		dao.crear(compromiso);
	}

	
	@EJB
	private DeudorEJB clienteEJB;

	@EJB
	private DeudaEJB deudaEJB;

	/**
	 * Agrega un compromiso de una deuda a un cliente
	 * 
	 * @param dto,
	 *            dto con los datos del compromiso
	 * @return un numero dependiendo la validacion
	 */
	public int agregarCompromiso(CompromisoDTO dto) {
		Deuda deuda = deudaEJB.buscar(dto.getDeuda());
		Cliente cliente = clienteEJB.buscar(dto.getCedula());
		Date hoy = Calendar.getInstance().getTime();
		listarCompromisosDeudor(dto.getCedula());
		int fechas = hoy.compareTo(dto.getFechaPagoMax());
		if (cliente != null) {
			if (deuda != null) {
				if (fechas != 1) {
					if (dto.getCedula().equals(deuda.getCliente().getCedula())) {
						if (numero != 9) {
							Compromiso compromiso = new Compromiso(dto.getDescripcion(), dto.getFechaPagoMax(), deuda,
									numero, false, dto.getValor());
							dao.crear(compromiso);
							return 0;// agregado con existo
						} else {
							return 3;// error, excedio el numero de compromisos
										// para esta deuda, reportarlo
						}
					} else {
						return 1;// el codigo de la deuda no concuerda con el
									// cliente
					}
				} else {
					return 5;// la fecha ingresada ya vencio
				}
			} else {
				return 2;// esta deuda no existe
			}
		} else {
			return 4;// este cliente no existe
		}
	}

	/**
	 * lista los compromisos de un deudor
	 * 
	 * @param cedula
	 *            la cedula del deudor
	 * @return una lista con los compromisos del deudor
	 */
	public List<Compromiso> listarCompromisosDeudor(String cedula) {
		numero = 0;
		List<Compromiso> lista = dao.ejecutarNamedQuery(Compromiso.LISTA_COMPROMISOS_DEUDOR, cedula);
		numero = lista.size() + 1;
		return lista;
	}

	/**
	 * lista los compromisos de un deudor
	 * 
	 * @param cedula
	 *            la cedula del deudor
	 * @return una lista con los compromisos del deudor
	 */
	public List<Compromiso> listarCompromisosCedula(String cedula) {
		return dao.ejecutarNamedQuery(Compromiso.LISTA_COMPROMISOS_DEUDOR, cedula);
	}

	/**
	 * lista los comrpomisos de los deudores
	 * 
	 * @return una lista con los compromisos de los deudores
	 */
	public List<Compromiso> listarDeudores() {
		List<Compromiso> lista = dao.ejecutarNamedQuery(Compromiso.LISTA_DEUDORES);
		List<Compromiso> lista1 = dao.ejecutarNamedQuery(Compromiso.LISTA_DEUDORES1);
		for (int i = 0; i < lista.size(); i++) {
			for (int j = 0; j < lista1.size(); j++) {
				if (!lista.isEmpty()) {
					if (lista.get(i).getDeuda().getCliente().getCedula() == lista1.get(j).getDeuda().getCliente()
							.getCedula()) {
						lista.remove(i);
					}
				}

			}
		}
		return lista;
	}

	/**
	 * lista los compromisos por dias en mora
	 * 
	 * @param dias
	 *            los dias para los compromisos
	 * @return una lista con los compromisos que estan en los dias ingresados
	 */
	public List<Compromiso> listarCompromisoPorDiasMora(double dias) {
		List<Compromiso> compromisos = dao.ejecutarNamedQuery(Compromiso.LISTA_COMPROMISOS);
		List<Compromiso> lista = new ArrayList<>();
		Date fechaHoy = Calendar.getInstance().getTime();
		long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		for (int i = 0; i < compromisos.size(); i++) {
			double resta = (fechaHoy.getTime() - compromisos.get(i).getDeuda().getFecha().getTime()) / MILLSECS_PER_DAY;
				if (resta == dias) {
				lista.add(compromisos.get(i));
			}
		}
		return lista;
	}

	/**
	 * Lista los compromisos de hoy
	 * 
	 * @return una lista de compromisos de hoy
	 */
	public List<Compromiso> listarCompromisosHoy() {
		return dao.ejecutarNamedQuery(Compromiso.LISTA_COMPROMISOS_FECHA, Calendar.getInstance().getTime());
	}

	/**
	 * Lista los compromisos de hoy
	 * 
	 * @return una lista de compromisos de hoy
	 */
	public List<Compromiso> listarCompromisosFecha(Date fecha) {
		return dao.ejecutarNamedQuery(Compromiso.LISTA_COMPROMISOS_FECHA, fecha);
	}

	/**
	 * Lista los compromisos por tipo
	 * 
	 * @param tipo
	 *            el tipo del compromiso
	 * @return una lista de compromisos por tipo
	 */
	public List<Compromiso> listarCOmpromisosTipo(int tipo) {
		return dao.ejecutarNamedQuery(Compromiso.LISTA_COMPROMISOS_TIPO, tipo);

	}

}
