package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="nekretnine_table")
public class Nekretnina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	private TipNekretnine tip;
	
	@NotBlank
	private ZonaNekretnine zona;
	
	@NotBlank
	private double kvadratura;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hipoteka_id", nullable = false)
    private Hipoteka hipoteka;
	
	private double procenjenaVrednost;

	public Nekretnina() {
		super();
	}

	public Nekretnina(Long id, @NotBlank TipNekretnine tip, @NotBlank ZonaNekretnine zona, @NotBlank double kvadratura,
			double procenjenaVrednost) {
		super();
		this.id = id;
		this.tip = tip;
		this.zona = zona;
		this.kvadratura = kvadratura;
		this.procenjenaVrednost = procenjenaVrednost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipNekretnine getTip() {
		return tip;
	}

	public void setTip(TipNekretnine tip) {
		this.tip = tip;
	}

	public ZonaNekretnine getZona() {
		return zona;
	}

	public void setZona(ZonaNekretnine zona) {
		this.zona = zona;
	}

	public double getKvadratura() {
		return kvadratura;
	}

	public void setKvadratura(double kvadratura) {
		this.kvadratura = kvadratura;
	}

	public double getProcenjenaVrednost() {
		return procenjenaVrednost;
	}

	public void setProcenjenaVrednost(double procenjenaVrednost) {
		this.procenjenaVrednost = procenjenaVrednost;
	}
	
}
