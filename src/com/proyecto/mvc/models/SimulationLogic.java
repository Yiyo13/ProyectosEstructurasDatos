package com.proyecto.mvc.models;

public class SimulationLogic {

	private CircularDoubleList flights;
	private Graph graph;
	private int elapsedSeconds;
	private int skipped;

	public SimulationLogic(CircularDoubleList flights, Graph graph) {
		this.flights = flights;
		this.graph = graph;
		this.elapsedSeconds = 0;
		this.skipped = 0;
	}

	public void rebuild() {

		Flight[] all = flights.getAllFlights();

		for (int i = 0; i < all.length; i++) {

			if (Flight.LANDED.equals(all[i].isStatus())) {

				Vertex destination = graph.findVertex(all[i].getDestination());

				if (destination != null && !destination.getLanded().contains(all[i])) {
					destination.getLanded().add(all[i]);
				}
			}
		}
	}

	public int start() {// pone en transito los vuelos programados, devuelve cuantos salieron

		int started = 0;
		skipped = 0;
		Flight[] all = flights.getAllFlights();

		for (int i = 0; i < all.length; i++) {

			Flight flight = all[i];

			if (Flight.SCHEDULED.equals(flight.isStatus())) {

				Vertex destination = graph.findVertex(flight.getDestination());
				int km = graph.shortestDistance(flight.getOrigin(), flight.getDestination());

				if (destination == null || km < 0) {// aeropuerto inexistente o sin ruta
					skipped++;
				} else {
					flight.startFlight(km);
					destination.getInbound().add(flight);
					started++;
				}
			}
		}
		return started;
	}

	public boolean tick() {// avanza 1 segundo, true si aun hay vuelos en transito

		boolean active = false;
		Flight[] all = flights.getAllFlights();// copia, es seguro mover vuelos entre listas
		elapsedSeconds++;

		for (int i = 0; i < all.length; i++) {

			Flight flight = all[i];

			if (Flight.IN_TRANSIT.equals(flight.isStatus())) {

				if (flight.advance()) {

					Vertex destination = graph.findVertex(flight.getDestination());
					destination.getInbound().remove(flight);
					destination.getPending().add(flight);

				} else {
					active = true;
				}
			}
		}
		return active;
	}

	public void reset() {

		VertexNode aux = graph.getStart();
		while (aux != null) {
			aux.getVertex().clearFlights();
			aux = aux.getNext();
		}

		Flight[] all = flights.getAllFlights();
		for (int i = 0; i < all.length; i++) {
			all[i].resetSimulation();
		}
		elapsedSeconds = 0;
		skipped = 0;
	}

	public Flight[] getAllFlights() {
		return flights.getAllFlights();
	}

	public int getElapsedSeconds() {
		return elapsedSeconds;
	}

	public int getSkipped() {
		return skipped;
	}
}