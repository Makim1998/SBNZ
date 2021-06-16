package com.example.dto;

import java.util.List;

import com.example.model.Hipoteka;
import com.example.model.Nekretnina;

public class HipotekaDTO {
	private List<Nekretnina> nekretnine;
	private boolean status;

	public HipotekaDTO() {
		super();
	}

	public HipotekaDTO(List<Nekretnina> nekretnine, boolean status) {
		super();
		this.nekretnine = nekretnine;
		this.status = status;
	}

	public HipotekaDTO(Hipoteka hipoteka) {
		this.nekretnine = hipoteka.getNekretnine();
		this.status = hipoteka.isStatus();
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
