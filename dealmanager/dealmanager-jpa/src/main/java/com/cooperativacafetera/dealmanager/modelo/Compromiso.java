package com.cooperativacafetera.dealmanager.modelo;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "compromiso")
@NamedQueries({ @NamedQuery(name = Compromiso.LISTA_DEUDORES, query = "select c from Compromiso c where c.tipo=0"),
		@NamedQuery(name = Compromiso.LISTA_DEUDORES1, query = "select c from Compromiso c where c.tipo=1"),
		@NamedQuery(name = Compromiso.LISTA_COMPROMISOS, query = "select c from Compromiso c where c.tipo>0"),
		@NamedQuery(name = Compromiso.LISTA_COMPROMISOS_TIPO, query = "select c from Compromiso c where c.tipo=?1"),
		@NamedQuery(name = Compromiso.LISTA_COMPROMISOS_DEUDOR, query = "select c from Compromiso c where c.deuda.cliente.cedula=?1 and c.tipo > 0"),
		@NamedQuery(name = Compromiso.LISTA_COMPROMISOS_FECHA, query = "select c from Compromiso c where c.fechaPagoMax=?1"),
		@NamedQuery(name = Compromiso.LISTA_COMPROMISOS_DEUDA, query = "select c from Compromiso c where c.deuda.cliente=?1") })
public class Compromiso implements Serializable {

	public static final String LISTA_COMPROMISOS = "Compromsio.compromisos";
	public static final String LISTA_COMPROMISOS_TIPO = "Compromsio.listarTipo";
	public static final String LISTA_COMPROMISOS_FECHA = "Compromsio.listarHoy";
	public static final String LISTA_DEUDORES = "Compromiso.listarDeudores";
	public static final String LISTA_DEUDORES1 = "Compromiso.listarDeudores1";
	public static final String LISTA_COMPROMISOS_DEUDOR = "Compromiso.listarCompromisos";
	public static final String LISTA_COMPROMISOS_DEUDA = "Compromiso.ListaCompromisosDeuda";

	@Id
	@Column(name = "codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "deuda")
	private Deuda deuda;

	@Column(name = "descripcion")
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_pago_max")
	private Date fechaPagoMax;

	@Column(name = "tipo_compromiso")
	private int tipo;

	@Column(name = "estado")
	private boolean estado;

	@Column
	private double valor;

	public Compromiso() {
	}

	public Compromiso(String descripcion, Date fechaPagoMax, Deuda deuda, int tipo, boolean estado, double valor) {
		super();
		this.descripcion = descripcion;
		this.fechaPagoMax = fechaPagoMax;
		this.deuda = deuda;
		this.tipo = tipo;
		this.estado = estado;
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaPagoMax() {
		return fechaPagoMax;
	}

	public void setFechaPagoMax(Date fechaPagoMax) {
		this.fechaPagoMax = fechaPagoMax;
	}

	public Deuda getDeuda() {
		return deuda;
	}

	public void setDeuda(Deuda deuda) {
		this.deuda = deuda;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
