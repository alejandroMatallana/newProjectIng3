package com.cooperativacafetera.dealmanager.dto;

import java.util.Date;

public class MoraDTO {

	private String idQuota;
	private String idClient;
	private String name;
	private String email;
	private String phoneNumber;
	private Date fecha;
	private double quotaTotal;

	public MoraDTO() {
	}

	public MoraDTO(String idQuota, String idClient, String name, String email, String phoneNumber, Date fecha,
			double quotaTotal) {
		super();
		this.idQuota = idQuota;
		this.idClient = idClient;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.fecha = fecha;
		this.quotaTotal = quotaTotal;
	}

	public String getIdQuota() {
		return idQuota;
	}

	public void setIdQuota(String idQuota) {
		this.idQuota = idQuota;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getQuotaTotal() {
		return quotaTotal;
	}

	public void setQuotaTotal(double quotaTotal) {
		this.quotaTotal = quotaTotal;
	}

}
