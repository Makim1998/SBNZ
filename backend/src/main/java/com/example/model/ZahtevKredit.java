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
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="zahtevi_table")
public class ZahtevKredit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	private double iznos;
	
	@NotBlank
	private int period;
	
	@NotBlank
	private TipKredita tipKredita;

	@NotBlank
	private TipGarancije tipGarancije;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "kredit_id", nullable = false)
	private Kredit kredit;
	
	public ZahtevKredit() {
		super();
	}

	public ZahtevKredit(Long id, @NotBlank double iznos, @NotBlank int period, @NotBlank TipKredita tipKredita,
			TipGarancije tipGarancije) {
		super();
		this.id = id;
		this.iznos = iznos;
		this.period = period;
		this.tipKredita = tipKredita;
		this.tipGarancije = tipGarancije;
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
	
}
