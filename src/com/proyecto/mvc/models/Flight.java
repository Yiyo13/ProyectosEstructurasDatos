package com.proyecto.mvc.models;

public class Flight {

	
	
	private int flightNumber;
	private String route;
	private String planeType;
	private int maximumCapacity;
	private String status;
	private DoubleListPassenger seats;//asientos
	private BoardingQueue boardingQueue;
	
	public Flight( int flightNumber, String route, String planeType, int maximumCapacity, String status) {
		
		this.flightNumber = flightNumber;
		this.route = route;
		this.planeType = planeType;
		this.maximumCapacity = maximumCapacity;
		this.status = status;
		
		this.seats = new DoubleListPassenger(maximumCapacity);
		this.boardingQueue = new BoardingQueue();
		
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
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
	
	public double getOccupancyPercentage() {
		
		return (double) this.seats.getCurrentCapacity()/this.maximumCapacity;
	}
	
	public int getAvailableSeats() {
		
		return this.maximumCapacity - this.seats.getCurrentCapacity();
	}
	
	public BoardingQueue getBoardingQueue() {
	    return boardingQueue;
	}

	@Override
	public String toString() {
	    return "Vuelo " + flightNumber + "\n" +
	           "  Ruta: "      + route       + "\n" +
	           "  Avión: "     + planeType   + "\n" +
	           "  Capacidad: " + maximumCapacity + "\n" +
	           "  Pasajeros registrados: " + this.seats.getCurrentCapacity() + "\n" +
	           "  Espacios disponibles: "  + getAvailableSeats() + "\n" +
	           "  Estado: "    + status;
	}
	
	public String printReservations() {
		
		return "Vuelo " + flightNumber + "\n" +
				 "  Ruta: "      + route       + "\n" +
				 "  Capacidad: " + maximumCapacity + "\n" +
				 "  Pasajeros registrados: " + this.seats.getCurrentCapacity() + "\n" +
				 "  Espacios disponibles: "  + getAvailableSeats() + "\n\n" +
				getSeats().toString();
		
	}

}