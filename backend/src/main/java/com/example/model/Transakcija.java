package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Transakcija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	private double iznos;
	
	@NotBlank
	private TipTransakcije tip;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klijent_id", nullable = false)
	private Klijent klijent;

	public Transakcija() {
		super();
	}

	public Transakcija(Long id, @NotBlank double iznos, @NotBlank TipTransakcije tip, Klijent klijent) {
		super();
		this.id = id;
		this.iznos = iznos;
		this.tip = tip;
		this.klijent = klijent;
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

	public TipTransakcije getTip() {
		return tip;
	}

	public void setTip(TipTransakcije tip) {
		this.tip = tip;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}	

}
