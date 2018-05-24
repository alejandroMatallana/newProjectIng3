package com.cooperativacafetera.dealmanager.dto;

import java.util.Date;

import com.cooperativacafetera.dealmanager.modelo.Deuda;

public class CompromisoDTO {

	private String cedula;
	private String descripcion;
	private Date fechaPagoMax;
	private String deuda;
	private double valor;

	public CompromisoDTO() {
	}

	public CompromisoDTO(String cedula, String descripcion, Date fechaPagoMax, String deuda, double valor) {
		super();
		this.cedula = cedula;
		this.descripcion = descripcion;
		this.fechaPagoMax = fechaPagoMax;
		this.deuda = deuda;
		this.valor = valor;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public String getDeuda() {
		return deuda;
	}

	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
