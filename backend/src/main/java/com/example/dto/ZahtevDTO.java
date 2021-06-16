package com.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.model.ZahtevKredit;

public class ZahtevDTO {
	
	private long id;
	
	@NotNull
	private double iznos;
	
	@NotNull
	private int period;
	
	@NotBlank
	private String tip;
	
	@NotBlank
	private String garancija;
	
	private boolean status;
	
	private String odgovor;
	
	public ZahtevDTO() {
		super();
	}

	public ZahtevDTO(long id, double iznos, int period_isplate, String tip, String garancija, boolean status) {
		super();
		this.id = id;
		this.iznos = iznos;
		this.period = period_isplate;
		this.tip = tip;
		this.garancija = garancija;
		this.status = status;
	}
	
	public ZahtevDTO(ZahtevKredit zahtev) {
		super();
		this.id = zahtev.getId();
		this.iznos = zahtev.getIznos();
		this.period = zahtev.getPeriod();
		this.tip = zahtev.getTipKredita().toString();
		this.garancija = zahtev.getTipGarancije().toString();
		this.status = zahtev.isStatus();
		this.odgovor = zahtev.getOdgovor();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public int getPeriod_isplate() {
		return period;
	}

	public void setPeriod_isplate(int period_isplate) {
		this.period = period_isplate;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getGarancija() {
		return garancija;
	}

	public void setGarancija(String garancija) {
		this.garancija = garancija;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}
		
}
