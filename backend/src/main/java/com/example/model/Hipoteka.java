package com.example.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Hipoteka {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klijent_id", nullable = false)
    private Klijent klijent;
	
	@OneToMany(mappedBy = "hipoteka", fetch = FetchType.LAZY)
	private List<Nekretnina> nekretnine;
	
	private boolean status;

	public Hipoteka() {
		super();
	}

	public Hipoteka(Long id, Klijent klijent, List<Nekretnina> nekretnine, boolean status) {
		super();
		this.id = id;
		this.klijent = klijent;
		this.nekretnine = nekretnine;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	public List<Nekretnina> getNekretnine() {
		return nekretnine;
	}

	public void setNekretnine(List<Nekretnina> nekretnine) {
		this.nekretnine = nekretnine;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
