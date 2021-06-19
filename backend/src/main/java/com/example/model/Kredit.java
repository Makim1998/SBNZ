package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Kredit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(mappedBy = "kredit", fetch = FetchType.LAZY)
	private ZahtevKredit zahtev;
	
	private double mesecna_rata;
	
	private double kamata;
	
	private int datumRate;
	
	private Date datumUgovora;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "klijent_id", nullable = true)
	private Klijent klijent;

	public Kredit() {
		super();
	}

	public Kredit(Long id, ZahtevKredit zahtev, double mesecna_rata, double kamata, int datumRate, Date datum) {
		super();
		this.id = id;
		this.zahtev = zahtev;
		this.mesecna_rata = mesecna_rata;
		this.kamata = kamata;
		this.datumRate = datumRate;
		this.datumUgovora = datum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZahtevKredit getZahtev() {
		return zahtev;
	}

	public void setZahtev(ZahtevKredit zahtev) {
		this.zahtev = zahtev;
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

	public int getDatumRate() {
		return datumRate;
	}

	public void setDatumRate(int datumRate) {
		this.datumRate = datumRate;
	}

	public Date getDatumUgovora() {
		return datumUgovora;
	}

	public void setDatumUgovora(Date datumUgovora) {
		this.datumUgovora = datumUgovora;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}
	
}
