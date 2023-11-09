package com.technohunk.dto;

import java.sql.Timestamp;

public class SignupDTO {

	private int nisha;
	private String password;
	private String email;
	private String name;
	private Timestamp doe;

	public int getNisha() {
		return nisha;
	}

	public void setNisha(int nisha) {
		this.nisha = nisha;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

}
