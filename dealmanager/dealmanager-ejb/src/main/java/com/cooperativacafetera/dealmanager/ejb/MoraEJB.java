package com.cooperativacafetera.dealmanager.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.cooperativacafetera.dealmanager.dto.MoraDTO;
import com.cooperativacafetera.dealmanager.implementacion.EJBGenerico;
import com.cooperativacafetera.dealmanager.modelo.Cliente;
import com.cooperativacafetera.dealmanager.modelo.Compromiso;
import com.cooperativacafetera.dealmanager.modelo.Deuda;

@Stateless
@Remote
public class MoraEJB extends EJBGenerico<Deuda> {

	@EJB
	private DeudorEJB deudorEJB;
	@EJB
	private DeudaEJB deudaEJB;
	@EJB
	private CompromisoEJB compromisoEJB;

	@Override
	public Class getClase() {
		return Deuda.class;
	}

	/**
	 * registra los usuario en mora
	 * 
	 * @param morasDTO
	 *            la lista de usuarios en mora
	 */
	public void registroMora(List<MoraDTO> morasDTO) {
		for (int i = 0; i < morasDTO.size(); i++) {
			Cliente cliente = deudorEJB.buscar(morasDTO.get(i).getIdClient());
			if (cliente == null) {
				cliente = new Cliente(morasDTO.get(i).getIdClient(), morasDTO.get(i).getName(),
						morasDTO.get(i).getPhoneNumber(), morasDTO.get(i).getEmail());
				deudorEJB.crear(cliente);
			}
			if (deudaEJB.buscar(morasDTO.get(i).getIdQuota()) == null) {
				Deuda deuda = new Deuda(morasDTO.get(i).getIdQuota(), cliente, morasDTO.get(i).getFecha(), false,
						morasDTO.get(i).getQuotaTotal());
				deudaEJB.crear(deuda);
				Compromiso compromiso = new Compromiso(null, null, deuda, 0, false, 0);
				compromisoEJB.crear(compromiso);
			}
		}
	}
}
