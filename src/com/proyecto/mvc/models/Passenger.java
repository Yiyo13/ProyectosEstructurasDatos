package com.proyecto.mvc.models;

public class Passenger {
	
	private int idP;
	private String nameP;
	private int ageP;
	
	
	public Passenger(int idP, String nameP, int ageP) {
		
		this.idP = idP;
		this.nameP = nameP;
		this.ageP = ageP;
	}


	public int getIdP() {
		return idP;
	}


	public void setIdP(int idP) {
		this.idP = idP;
	}


	public String getNameP() {
		return nameP;
	}


	public void setNameP(String nameP) {
		this.nameP = nameP;
	}


	public int getAgeP() {
		return ageP;
	}


	public void setAgeP(int ageP) {
		this.ageP = ageP;
	}


	@Override
	public String toString() {
		return "ID: " + idP + " | " + nameP + " | " + ageP + " años";
	}
	
	

}
