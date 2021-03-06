package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="klijent_table")
public class Klijent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private int godine;
	
	private double mesecna_zarada;
	
	@OneToMany(mappedBy = "klijent", fetch = FetchType.EAGER)
	private List<Kredit> krediti;
	
	@OneToMany(mappedBy = "klijent", fetch = FetchType.LAZY)
	private List<Transakcija> transakcije;
	
    @OneToOne(mappedBy = "klijent", fetch = FetchType.EAGER)
    private Hipoteka hipoteka;
	
    private double stanjeRacun;
    
	private int nagradni_poeni;
	
	private Date pridruzen;

	public Klijent() {
		super();
	}

	public Klijent(Long id, int godine, double mesecna_zarada, List<Kredit> krediti, List<Transakcija> transakcije,
			double stanjeRacun, int nagradni_poeni, Date pridruzen) {
		super();
		this.id = id;
		this.godine = godine;
		this.mesecna_zarada = mesecna_zarada;
		this.krediti = krediti;
		this.transakcije = transakcije;
		this.stanjeRacun = stanjeRacun;
		this.nagradni_poeni = nagradni_poeni;
		this.pridruzen = pridruzen;
	}
	
	public Klijent(int godine, double mesecna_zarada) {
		super();
		this.godine = godine;
		this.mesecna_zarada = mesecna_zarada;
		this.krediti = new ArrayList<Kredit>();
		this.transakcije = new ArrayList<Transakcija>();
		this.stanjeRacun = 0;
		this.nagradni_poeni = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGodine() {
		return godine;
	}

	public void setGodine(int godine) {
		this.godine = godine;
	}

	public double getMesecna_zarada() {
		return mesecna_zarada;
	}

	public void setMesecna_zarada(double mesecna_zarada) {
		this.mesecna_zarada = mesecna_zarada;
	}

	public List<Kredit> getKrediti() {
		return krediti;
	}

	public void setKrediti(List<Kredit> krediti) {
		this.krediti = krediti;
	}

	public List<Transakcija> getTransakcije() {
		return transakcije;
	}

	public void setTransakcije(List<Transakcija> transakcije) {
		this.transakcije = transakcije;
	}

	public Hipoteka getHipoteka() {
		return hipoteka;
	}

	public void setHipoteka(Hipoteka hipoteka) {
		this.hipoteka = hipoteka;
	}

	public double getStanjeRacun() {
		return stanjeRacun;
	}

	public void setStanjeRacun(double stanjeRacun) {
		this.stanjeRacun = stanjeRacun;
	}

	public int getNagradni_poeni() {
		return nagradni_poeni;
	}

	public void setNagradni_poeni(int nagradni_poeni) {
		this.nagradni_poeni = nagradni_poeni;
	}

	public Date getPridruzen() {
		return pridruzen;
	}

	public void setPridruzen(Date pridruzen) {
		this.pridruzen = pridruzen;
	}
	
}
