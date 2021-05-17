package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="zahtevi_table")
public class ZahtevKredit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	private double iznos;
	
	@NotNull
	private int period;
	
	private TipKredita tipKredita;

	private TipGarancije tipGarancije;
	
	private boolean status;
	
	private String odgovor;
	
	@OneToOne(targetEntity = Klijent.class)
	private Klijent klijent;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "kredit_id", nullable = true)
	private Kredit kredit;
	
	public ZahtevKredit() {
		super();
	}

	public ZahtevKredit(Long id, @NotNull double iznos, @NotNull int period, TipKredita tipKredita,
			TipGarancije tipGarancije, boolean status, String odgovor, Kredit kredit) {
		super();
		this.id = id;
		this.iznos = iznos;
		this.period = period;
		this.tipKredita = tipKredita;
		this.tipGarancije = tipGarancije;
		this.status = status;
		this.odgovor = odgovor;
		this.kredit = kredit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public TipKredita getTipKredita() {
		return tipKredita;
	}

	public void setTipKredita(TipKredita tipKredita) {
		this.tipKredita = tipKredita;
	}

	public TipGarancije getTipGarancije() {
		return tipGarancije;
	}

	public void setTipGarancije(TipGarancije tipGarancije) {
		this.tipGarancije = tipGarancije;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Kredit getKredit() {
		return kredit;
	}

	public void setKredit(Kredit kredit) {
		this.kredit = kredit;
	}

	public String getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}
	
}
