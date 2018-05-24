package com.cooperativacafetera.dealmanager.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Deuda")
@NamedQueries({
	@NamedQuery(name = Deuda.BUSCAR_DEUDA_CLIENTE, query = "select d from Deuda d where d.cliente=?1 and d.codigo=?2") })
public class Deuda implements Serializable {

	public static final String BUSCAR_DEUDA_CLIENTE = "Deuda.buscar";
	
	@Id
	@Column(name = "codigo")
	private String codigo;
	
	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	@Column
	private boolean estado;

	@Column
	private double valor;

	public Deuda() {
	}

	public Deuda(String codigo, Cliente cliente ,  Date fecha, boolean estado, double valor) {
		super();
		this.codigo = codigo;
		this.cliente = cliente;
		this.fecha = fecha;
		this.estado = estado;
		this.valor = valor;
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
