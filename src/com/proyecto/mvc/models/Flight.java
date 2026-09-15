package com.proyecto.mvc.models;

public class Flight {

	
	
	private String flightNumber;
	private String route;
	private String planeType;
	private int maximumCapicity;
	private String status;
	
	public Flight( String flightNumber, String route, String planeType, int maximumCapicity, String status) {
		
		this.flightNumber = flightNumber;
		this.route = route;
		this.planeType = planeType;
		this.maximumCapicity = maximumCapicity;
		this.status = status;
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

	public int getMaximumCapicity() {
		return maximumCapicity;
	}

	public void setMaximumCapicity(int maximumCapicity) {
		this.maximumCapicity = maximumCapicity;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", route=" + route + ", planeType=" + planeType
				+ ", maximumCapicity=" + maximumCapicity + ", status=" + status + "]";
	}

}
