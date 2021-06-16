package com.example.dto;

public class ZirantDTO {
	private long zirant;
	private long zahtev;
	
	public ZirantDTO() {
		super();
	}

	public ZirantDTO(long zirant, long zahtev) {
		super();
		this.zirant = zirant;
		this.zahtev = zahtev;
	}

	public long getZirant() {
		return zirant;
	}

	public void setZirant(long zirant) {
		this.zirant = zirant;
	}

	public long getZahtev() {
		return zahtev;
	}

	public void setZahtev(long zahtev) {
		this.zahtev = zahtev;
	}
	
}
