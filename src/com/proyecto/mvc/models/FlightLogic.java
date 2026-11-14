package com.proyecto.mvc.models;

public class FlightLogic {

	private FlightCircularList flights;
	private AVLTree avl;
	private FlightData flightD;
	private String flightsFile; 
	private SimulationLogic sm;

	public FlightLogic(FlightCircularList flights, AVLTree avl, FlightData flightD, String flightsFile) {
		this.flights = flights;
		this.avl = avl;
		this.flightD = flightD;
		this.flightsFile = flightsFile;
	}
	

	public SimulationLogic getSm() {
		return sm;
	}


	public void setSm(SimulationLogic sm) {
		this.sm = sm;
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
		boolean routeChanged = !flight.getOrigin().equals(origin) || !flight.getDestination().equals(destination);

		if (routeChanged && !Flight.SCHEDULED.equals(flight.isStatus())) {
			return "Solo se puede cambiar el origen o el destino si el vuelo esta Programado";
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
		if(sm != null) {
			sm.detach(flight);
		}
		flights.remove(flightNumber);
		avl.delete(flightNumber);
		flightD.saveFlights(flights, flightsFile);
		return null;
	}
	
	
	public Flight[] getPendingFlightsByAirport(String airportName) {
		
		int count = 0;
		Flight[] allFlights = flights.getAllFlights();
		
		for (int i = 0; i < allFlights.length; i++) {
			
			if(allFlights[i].getDestination().equalsIgnoreCase(airportName) && allFlights[i].isStatus().equals(Flight.PENDING)) {
				
				count++;
			}
		}
		
		Flight[] pending = new Flight[count];
		int index = 0;
		
		for (int i = 0; i < allFlights.length; i++) {
			
			if(allFlights[i].getDestination().equalsIgnoreCase(airportName) && allFlights[i].isStatus().equals(Flight.PENDING)) {
				
				pending[index++] = allFlights[i];
			}
		}
		
		
		return pending;
	}
	
	
	public Flight[] sortLandingPriority(Flight[] flights, StringBuilder trace) {

		Flight[] result = new Flight[flights.length];

		for (int i = 0; i < flights.length; i++) {
			result[i] = flights[i];
		}

		for (int i = 0; i < result.length; i++) {

			trace.append("--- Iteracion ").append(i + 1).append(" ---\n");
			trace.append("Pendientes (").append(result.length - i).append("):\n");

			for (int k = i; k < result.length; k++) {
				trace.append("   Vuelo ").append(result[k].getFlightNumber()).append(" | ")
						.append(result[k].getFuel()).append(" min\n");
			}

			int bestIndex = i;

			for (int j = i; j < result.length; j++) {

				if (result[j].getFuel() < result[bestIndex].getFuel()) {
					bestIndex = j;

				} else if (result[j].getFuel() == result[bestIndex].getFuel()
						&& result[j].getFlightNumber() < result[bestIndex].getFlightNumber()) {
					bestIndex = j;
				}
			}

			int ties = 0;
			for (int k = i; k < result.length; k++) {
				if (result[k].getFuel() == result[bestIndex].getFuel()) {
					ties++;
				}
			}

			Flight temp = result[i];
			result[i] = result[bestIndex];
			result[bestIndex] = temp;

			trace.append("Seleccionado: vuelo ").append(result[i].getFlightNumber())
					.append(" (combustible ").append(result[i].getFuel()).append(" min)");

			if (ties > 1) {
				trace.append("  <- empate en combustible, gana el menor numero de vuelo");
			}

			trace.append("\nOrden hasta ahora: ");
			for (int k = 0; k <= i; k++) {
				trace.append(result[k].getFlightNumber()).append(k < i ? " > " : "");
			}
			trace.append("\n\n");
		}

		return result;
	}
	public void save() {
	    flightD.saveFlights(flights, flightsFile);
	}
	
	
}
