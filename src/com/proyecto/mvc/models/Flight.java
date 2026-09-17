package com.proyecto.mvc.models;

public class Flight {

	
	
	private String flightNumber;
	private String route;
	private String planeType;
	private int maximumCapacity;
	private String status;
	private DoubleListPassenger seats;//asientos
	
	public Flight( String flightNumber, String route, String planeType, int maximumCapacity, String status) {
		
		this.flightNumber = flightNumber;
		this.route = route;
		this.planeType = planeType;
		this.maximumCapacity = maximumCapacity;
		this.status = status;
		
		this.seats = new DoubleListPassenger(maximumCapacity);
		
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public int getMaximumCapacity() {
		return maximumCapacity;
	}

	public void setMaximumCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public DoubleListPassenger getSeats() {
		return seats;
	}

	public void setSeats(DoubleListPassenger seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
	    return "Vuelo " + flightNumber + "\n" +
	           "  Ruta: "      + route       + "\n" +
	           "  Avión: "     + planeType   + "\n" +
	           "  Capacidad: " + maximumCapacity + "\n" +
	           "  Estado: "    + status;
	}
	
	public String printReservations() {
		
		return "Vuelo " + flightNumber + "\n" +
				 "  Ruta: "      + route       + "\n" +
				getSeats().toString();
		
	}

}
