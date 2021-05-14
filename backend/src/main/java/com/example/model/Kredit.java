package com.example.model;

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
	
	private int mesecna_rata;
	
	private int kamata;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klijent_id", nullable = false)
	private Klijent klijent;

	public Kredit() {
		super();
	}

	public Kredit(Long id, ZahtevKredit zahtev, int mesecna_rata, int kamata) {
		super();
		this.id = id;
		this.zahtev = zahtev;
		this.mesecna_rata = mesecna_rata;
		this.kamata = kamata;
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

	public int getMesecna_rata() {
		return mesecna_rata;
	}

	public void setMesecna_rata(int mesecna_rata) {
		this.mesecna_rata = mesecna_rata;
	}

	public int getKamata() {
		return kamata;
	}

	public void setKamata(int kamata) {
		this.kamata = kamata;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

}
