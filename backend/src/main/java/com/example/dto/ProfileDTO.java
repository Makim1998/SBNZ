package com.example.dto;

import javax.validation.constraints.NotBlank;

import com.example.model.User;

public class ProfileDTO {

	private long id;
	private String accessToken;
	private String role;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	private double mesecna_zarada;
	private int godine;
	
	public ProfileDTO() {
		super();
	}
	
	public ProfileDTO(User user, String accessToken) {
		super();
		this.id = user.getId();
		this.accessToken = accessToken;
		this.role = user.getAuthority().getName();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getMesecna_zarada() {
		return mesecna_zarada;
	}

	public void setMesecna_zarada(double mesecna_zarada) {
		this.mesecna_zarada = mesecna_zarada;
	}

	public int getGodine() {
		return godine;
	}

	public void setGodine(int godine) {
		this.godine = godine;
	}

}
