package com.proyecto.mvc.models;

public class SimulationLogic {

	private FlightCircularList flights;
	private Graph graph;
	private int elapsedSeconds;
	private int skipped;

	public SimulationLogic(FlightCircularList flights, Graph graph) {
		this.flights = flights;
		this.graph = graph;
		this.elapsedSeconds = 0;
		this.skipped = 0;
	}

	// CAMBIO: antes solo acomodar aterrizados. Ahora acomodar los tres estados.
	// Cavernicola: despues de prender programa, cada aeropuerto tener lista vacia.
	// Este metodo mirar cada vuelo y meter en la lista correcta del aeropuerto destino.
	public void rebuild() {

		// paso 1: cavernicola barrer cueva. Vaciar listas de todos los aeropuertos para no duplicar vuelos
		VertexNode aux = graph.getStart();
		while (aux != null) {
			aux.getVertex().clearFlights();
			aux = aux.getNext();
		}

		// paso 2: agarrar todos los vuelos y revisar uno por uno
		Flight[] all = flights.getAllFlights();

		for (int i = 0; i < all.length; i++) {

			Flight flight = all[i];
			Vertex destination = graph.findVertex(flight.getDestination());

			// si aeropuerto destino no existe, no se sabe donde aterrizar
			if (destination == null) {
				continue;
			}

			if (Flight.LANDED.equals(flight.isStatus())) {
				// vuelo ya aterrizo: va a lista de aterrizados del destino
				destination.getLanded().add(flight);

			} else if (Flight.PENDING.equals(flight.isStatus())) {
				// vuelo llego y espera permiso: va a lista de pendientes del destino
				destination.getPending().add(flight);

			} else if (Flight.IN_TRANSIT.equals(flight.isStatus())) {
				// vuelo estar volando: cavernicola calcular de nuevo cuanto falta (archivo no guarda segundos)
				int km = graph.shortestDistance(flight.getOrigin(), flight.getDestination());

				if (km < 0) {// ya no haber camino entre aeropuertos, vuelo no poder volar, volver a Programado
					flight.resetSimulation();
				} else {
					flight.startFlight(km);// viaje empezar de cero
					destination.getInbound().add(flight);// ponerlo en lista de "vienen en camino"
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
	// Vuelo salir de lista de pendientes del aeropuerto, cambiar a Aterrizado y entrar a lista de aterrizados.
	// Devuelve true si todo salio bien, false si vuelo no estar pendiente en ese aeropuerto.
	public boolean authorizeLanding(Flight flight) {

		Vertex destination = graph.findVertex(flight.getDestination());

		// si aeropuerto no existir o vuelo no estar esperando, cavernicola no hacer nada
		if (destination == null || !Flight.PENDING.equals(flight.isStatus())) {
			return false;
		}

		// sacar de la lista de pendientes. Si no estar ahi, algo raro, parar
		if (!destination.getPending().remove(flight)) {
			return false;
		}

		flight.setStatus(Flight.LANDED);// cambiar estado: ya aterrizo
		destination.getLanded().add(flight);// meter en lista de aterrizados (para puertas despues)
		return true;
	}
}