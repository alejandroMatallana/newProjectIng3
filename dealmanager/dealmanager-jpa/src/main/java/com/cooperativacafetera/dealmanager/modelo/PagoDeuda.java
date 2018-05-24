package com.cooperativacafetera.dealmanager.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pago_deuda")
@NamedQueries({
		@NamedQuery(name = PagoDeuda.LISTAR_PAGOS_CLIENTES, query = "select pd from PagoDeuda pd where pd.cliente=?1 and pd.deuda=?2") })
public class PagoDeuda implements Serializable {

	public static final String LISTAR_PAGOS_CLIENTES = "PagoDeuda.listar";
	
	@Id
	@Column(name = "codigo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "valor")
	private double valor;

	@Column(name = "interes")
	private double interes;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "deuda")
	private Deuda deuda;

	public PagoDeuda() {
	}

	public PagoDeuda(int codigo, Date fecha, double valor, double interes, Deuda deuda, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.valor = valor;
		this.interes = interes;
		this.deuda = deuda;
		this.cliente = cliente;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public Deuda getDeuda() {
		return deuda;
	}

	public void setDeuda(Deuda deuda) {
		this.deuda = deuda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
