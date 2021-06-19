package com.example.dto;

public class PonudaDTO {
	private long id;
	private int datum;
	private double kamata;
	private double rata;
	
	public PonudaDTO() {
		super();
	}
	
	public PonudaDTO(long id, int datum, double kamata, double rata) {
		super();
		this.id = id;
		this.datum = datum;
		this.kamata = kamata;
		this.rata = rata;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDatum() {
		return datum;
	}
	public void setDatum(int datum) {
		this.datum = datum;
	}
	public double getKamata() {
		return kamata;
	}
	public void setKamata(double kamata) {
		this.kamata = kamata;
	}
	public double getRata() {
		return rata;
	}
	public void setRata(double rata) {
		this.rata = rata;
	}

}
