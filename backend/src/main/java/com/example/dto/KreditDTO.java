package com.example.dto;

import java.util.Date;

import com.example.model.Kredit;

public class KreditDTO {
	private Date ugovoren;
	private double iznos;
	private int periodIsplate;
	private double mesecna_rata;
	private double kamata;
	
	public KreditDTO() {
		super();
	}
	
	public KreditDTO(Date ugovoren, double iznos, int periodIsplate, double mesecna_rata, double kamata) {
		super();
		this.ugovoren = ugovoren;
		this.iznos = iznos;
		this.periodIsplate = periodIsplate;
		this.mesecna_rata = mesecna_rata;
		this.kamata = kamata;
	}
	
	public KreditDTO(Kredit kredit) {
		this.ugovoren = kredit.getDatumUgovora();
		this.iznos = kredit.getZahtev().getIznos();
		this.periodIsplate = kredit.getZahtev().getPeriod();
		this.mesecna_rata = kredit.getMesecna_rata();
		this.kamata = kredit.getKamata();
	}

	public Date getUgovoren() {
		return ugovoren;
	}
	public void setUgovoren(Date ugovoren) {
		this.ugovoren = ugovoren;
	}
	public double getIznos() {
		return iznos;
	}
	public void setIznos(double iznos) {
		this.iznos = iznos;
	}
	public int getPeriodIsplate() {
		return periodIsplate;
	}
	public void setPeriodIsplate(int periodIsplate) {
		this.periodIsplate = periodIsplate;
	}
	public double getMesecna_rata() {
		return mesecna_rata;
	}
	public void setMesecna_rata(double mesecna_rata) {
		this.mesecna_rata = mesecna_rata;
	}
	public double getKamata() {
		return kamata;
	}
	public void setKamata(double kamata) {
		this.kamata = kamata;
	}

}
