package com.proyecto.mvc.models;

public class FlightLogic {

	private CircularDoubleList flights;
	private AVLTree avl;
	private FlightData flightD;
	private String flightsFile;

	public FlightLogic(CircularDoubleList flights, AVLTree avl, FlightData flightD, String flightsFile) {
		this.flights = flights;
		this.avl = avl;
		this.flightD = flightD;
		this.flightsFile = flightsFile;
	}

	public void buildTree() {// llena avl desde lista

		Flight[] allFlights = flights.getAllFlights();

		for (int i = 0; i < allFlights.length; i++) {
			avl.insert(allFlights[i]);
		}
	}

	public String validate(String origin, String destination, String capacity, String fuel) {

		if (origin == null || destination == null) {
			return "Debe registrar aeropuertos primero";
		}
		if (origin.equals(destination)) {
			return "El origen y el destino no pueden ser iguales";
		}

		try {
			if (Integer.parseInt(capacity.trim()) <= 0 || Integer.parseInt(fuel.trim()) <= 0) {
				return "La capacidad y el combustible deben ser mayores a 0";
			}
		} catch (NumberFormatException e) {
			return "La capacidad y el combustible deben ser numeros";
		}

		return null;
	}

	public String register(Flight flight) {

		if (flight.getFlightNumber() <= 0) {
			return "El numero de vuelo debe ser mayor a 0";
		}
		if (avl.search(flight.getFlightNumber()) != null) {
			return "El numero de vuelo ya existe";
		}

		flights.add(flight);
		avl.insert(flight);
		flightD.saveFlights(flights, flightsFile);
		return null;
	}

	public String update(Flight flight, String origin, String destination, String planeType, int capacity, int fuel) {

		if (capacity < flight.getSeats().getCurrentCapacity()) {
			return "La capacidad no puede ser menor a los pasajeros registrados";
		}

		flight.setOrigin(origin);// mismo objeto en lista y avl
		flight.setDestination(destination);
		flight.setPlaneType(planeType);
		flight.setMaximumCapacity(capacity);
		flight.setFuel(fuel);

		flightD.saveFlights(flights, flightsFile);
		return null;
	}

	public String delete(int flightNumber) {

		Flight flight = avl.search(flightNumber);

		if (flight == null) {
			return "El vuelo " + flightNumber + " no existe";
		}
		if (flight.getSeats().getCurrentCapacity() > 0) {
			return "El vuelo " + flightNumber + " tiene pasajeros, no se puede eliminar";
		}

		flights.remove(flightNumber);
		avl.delete(flightNumber);
		flightD.saveFlights(flights, flightsFile);
		return null;
	}
}
