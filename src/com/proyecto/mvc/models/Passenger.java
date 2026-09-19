package com.proyecto.mvc.models;

public class Passenger {
	
	private int idP;
	private String nameP;
	private int ageP;
	private boolean boarded;
	
	
	public Passenger(int idP, String nameP, int ageP) {
		
		this.idP = idP;
		this.nameP = nameP;
		this.ageP = ageP;
		this.boarded = false;
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
	
	public boolean isBoarded() {
	    return boarded;
	}

	public void setBoarded(boolean boarded) {
	    this.boarded = boarded;
	}


	@Override
	public String toString() {
		return "ID: " + idP + " | " + nameP + " | " + ageP + " a�os";
	}
	
	

}
