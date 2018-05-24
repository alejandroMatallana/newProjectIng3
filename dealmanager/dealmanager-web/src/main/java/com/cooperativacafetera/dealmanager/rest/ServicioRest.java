package com.cooperativacafetera.dealmanager.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cooperativacafetera.dealmanager.dto.CompromisoDTO;
import com.cooperativacafetera.dealmanager.dto.MoraDTO;
import com.cooperativacafetera.dealmanager.ejb.CompromisoEJB;
import com.cooperativacafetera.dealmanager.ejb.DeudaEJB;
import com.cooperativacafetera.dealmanager.ejb.DeudorEJB;
import com.cooperativacafetera.dealmanager.ejb.MoraEJB;
import com.cooperativacafetera.dealmanager.ejb.PagoDeudaEJB;
import com.cooperativacafetera.dealmanager.modelo.Cliente;
import com.cooperativacafetera.dealmanager.modelo.Compromiso;
import com.cooperativacafetera.dealmanager.modelo.Deuda;
import com.cooperativacafetera.dealmanager.modelo.PagoDeuda;
import com.cooperativacafetera.dealmanager.web.util.RespuestaDTO;

@Path("/servicios")
public class ServicioRest {

	@EJB
	private DeudaEJB deudaEJB;

	@EJB
	private DeudorEJB deudorEJB;

	@EJB
	private PagoDeudaEJB pagoDeudaEJB;

	@EJB
	private MoraEJB moraEJB;

	@EJB
	private CompromisoEJB compromisoEJB;

	public ServicioRest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * busca un cliente o deudor
	 * 
	 * @param cedula
	 *            la cedula del deudor
	 * @return un dto con el deudor
	 */
	@POST
	@Path("/buscarCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscarDeudor(@FormParam(value = "cedula") String cedula) {
		Cliente cliente = deudorEJB.buscar(cedula);
		if (cliente != null) {
			return new RespuestaDTO(cliente);
		} else {
			return new RespuestaDTO(null, "No se encontro el cliente con la cedula " + cedula, "-1");
		}
	}

	/**
	 * busca una deuda
	 * 
	 * @param codigo
	 *            el codigo de la deuda a buscar
	 * @return un dto con la deuda
	 */
	@POST
	@Path("/buscarDeuda")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscarDeuda(@FormParam(value = "codigo") String codigo) {
		Deuda deuda = deudaEJB.buscar(codigo);
		if (deuda != null) {
			return new RespuestaDTO(deuda);
		} else {
			return new RespuestaDTO(null, "No se encontro una deuda con el codigo " + codigo, "-1");
		}
	}

	/**
	 * crea un pago
	 * 
	 * @param pagoDeuda
	 *            el pago a crear
	 * @return dto informando si se pudo crear o no
	 */
	@POST
	@Path("/crearPago")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearPago(PagoDeuda pagoDeuda) {
		try {
			int resultado = pagoDeudaEJB.crearPagoDeuda(pagoDeuda);
			if (resultado == 0) {
				pagoDeudaEJB.crear(pagoDeuda);
				return new RespuestaDTO(true, "Se registro el pago", "00");
			} else if (resultado == 1) {
				return new RespuestaDTO(false, "Señor usuario el valor ingresado es mayor al de la deuda", "-1");
			} else {
				return new RespuestaDTO(false, "Esta deuda no corresponde para el usuario ingresado", "-2");
			}
		} catch (NumberFormatException e) {
			return new RespuestaDTO(false, "Ha ingresado letras en campos numericos", "-3");
		}
	}

	/**
	 * busca los pagos de un cliente
	 * 
	 * @param cliente
	 *            el cliente
	 * @param deuda
	 *            la deuda del cliente
	 * @return una lista con los pagos realizados
	 */
	@POST
	@Path("/buscarPagos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO verPagos(@FormParam(value = "cedula") String cedula,
			@FormParam(value = "codigo") String codigo) {
		Cliente cliente = deudorEJB.buscar(cedula);
		Deuda deuda = deudaEJB.buscar(codigo);
		List<PagoDeuda> lista = pagoDeudaEJB.listarPagos(cliente, deuda);
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No se encontraron pagos con los datos ingresados", "-1");
		}
	}

	/**
	 * registra un cliente en mora
	 * 
	 * @param clienteDeudaDTO
	 *            el cliente y la deuda a registrar
	 * @return una respuesta informando si se pudo registrar o no
	 */
	@POST
	@Path("/registroMora")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO registrarMora(List<MoraDTO> morasDTO) {
		moraEJB.registroMora(morasDTO);
		return new RespuestaDTO(true, "Se actualizo el registro en cartera", "00");
	}

	/**
	 * lista los deudores
	 * 
	 * @return una lista con los deudores
	 */
	@Path("/listarDeudores")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarDeudores() {
		List<Compromiso> lista = compromisoEJB.listarDeudores();
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {

			return new RespuestaDTO(false, "error al cargar deudores", "-2");
		}
	}

	/**
	 * lista los compromisos de hoy
	 * 
	 * @return una respuesta indicando si hay o no compromisos para hoy
	 */
	@POST
	@Path("/listarCompromisosTipo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO compromisosTipo(@FormParam(value = "tipo") int tipo) {
		List<Compromiso> compromiso = compromisoEJB.listarCOmpromisosTipo(tipo);
		if (compromiso.isEmpty()) {
			return new RespuestaDTO(false, "No hay compromisos con el tipo ingresado", "-1");
		} else {
			return new RespuestaDTO(compromiso);
		}
	}

	/**
	 * lista los compromisos de hoy
	 * 
	 * @return una respuesta indicando si hay o no compromisos para hoy
	 */
	@Path("/listarCompromisosHoy")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO compromisosHoy() {
		List<Compromiso> compromiso = compromisoEJB.listarCompromisosHoy();
		if (compromiso.isEmpty()) {
			return new RespuestaDTO(false, "No hay compromisos para la fecha de hoy", "-1");
		} else {
			return new RespuestaDTO(compromiso);
		}
	}

	/**
	 * lista los compromisos de hoy
	 * 
	 * @return una respuesta indicando si hay o no compromisos para hoy @throws
	 */
	@POST
	@Path("/listarCompromisosFecha")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO compromisosFecha(@FormParam(value = "fecha") Date fecha) {
		List<Compromiso> compromiso = compromisoEJB.listarCompromisosFecha(fecha);
		if (compromiso.isEmpty()) {
			return new RespuestaDTO(false, "No hay compromisos para la fecha ingresada", "-1");
		} else {
			return new RespuestaDTO(compromiso);
		}
	}

	/**
	 * lista los compromisos por los dias ingresados
	 * 
	 * @param dias
	 *            los dias para los compromisos
	 * @return una lista con los compromisos que entan en los dias ingresados
	 */
	@Path("/listarCompromisosDias")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO listarCompromisoDias(@FormParam(value = "dias") double dias) {
		List<Compromiso> lista = compromisoEJB.listarCompromisoPorDiasMora(dias);
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay compromisos para los dias ingresados", "-2");
		}
	}

	/**
	 * lista los compromisos de un cliente
	 * 
	 * @param cedula
	 *            la cedula del cliente
	 * @return la lista de compromisos de un cliente
	 */
	@Path("/listarCompromisosCedula")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO listarCompromisoCedula(@FormParam(value = "cedula") String cedula) {
		List<Compromiso> lista = compromisoEJB.listarCompromisosCedula(cedula);
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay compromisos para la cedula ingresada", "-2");
		}
	}

	/**
	 * lista los compromisos de un deudor por su cedula
	 * 
	 * @param cedula
	 *            la cedula del deudor
	 * @return una lista de los compromisos de un deudor
	 */
	@Path("/listarCompromisosDeudor")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO listarCompromisosDeudor(@FormParam(value = "cedula") String cedula) {
		List<Compromiso> lista = compromisoEJB.listarCompromisosDeudor(cedula);
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay compromisos registrados a este numero de documento", "-2");
		}
	}

	/**
	 * agrega un compromiso a un deudor
	 * 
	 * @param dto
	 *            la informacion solicitada para crear el compromiso al deudor
	 * @return una respuesta con diciendo si se pudo agregar o no
	 */
	@Path("/agregarCompromiso")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO agregarCompromiso(CompromisoDTO dto) {
		int mnj = compromisoEJB.agregarCompromiso(dto);
		if (mnj == 0) {
			return new RespuestaDTO(true, "compromiso agregado con exito", "00");
		} else if (mnj == 3) {
			return new RespuestaDTO(false, "error, excedio el numero de compromisos para esta deuda, reportarlo", "-3");
		} else if (mnj == 1) {
			return new RespuestaDTO(false, "Esta deuda no corresponde para el usuario ingresado", "-1");
		} else if (mnj == 2) {
			return new RespuestaDTO(false, "Esta deuda no existe", "-2");
		} else if (mnj == 4) {
			return new RespuestaDTO(false, "No existe un cliente moroso con esta cedula", "-4");
		} else {
			return new RespuestaDTO(false, "La fecha ingresada ya vencio", "-4");

		}

	}
}