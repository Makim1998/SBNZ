package com.example.dto;

import java.util.Date;

import com.example.model.Transakcija;

public class TransakcijaDTO {
	private Date datum;
	private String tip;
	private double iznos;
	
	public TransakcijaDTO() {
		super();
	}

	public TransakcijaDTO(Date datum, String tip, double iznos) {
		super();
		this.datum = datum;
		this.tip = tip;
		this.iznos = iznos;
	}
	
	public TransakcijaDTO(Transakcija t) {
		super();
		this.datum = t.getDatum();
		this.tip = t.getTip().toString();
		this.iznos = t.getIznos();
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}
	
}
