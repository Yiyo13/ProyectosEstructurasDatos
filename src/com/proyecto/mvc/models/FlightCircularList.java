package com.proyecto.mvc.models;

// Lista circular doble de vuelos (la del Proyecto 1).
// Toda la logica de nodos esta en CircularDoubleList<T>; aqui solo se dice
// COMO se busca un vuelo (por numero) y COMO se ordenan (Modo Torre de Control).
public class FlightCircularList extends CircularDoubleList<Flight> {

	public boolean checkDuplicateFlightNumber(int flightNumber) {
		return contains(f -> f.getFlightNumber() == flightNumber);
	}

	public Flight findFlight(int flightNumber) {
		return find(f -> f.getFlightNumber() == flightNumber);
	}

	public boolean remove(int flightNumber) {
		return remove(f -> f.getFlightNumber() == flightNumber);
	}

	public boolean selectFlight(int flightNumber) {// mueve "current" directo al vuelo indicado
		return select(f -> f.getFlightNumber() == flightNumber);
	}

	// Modo Torre de Control: mayor ocupacion primero; si empatan, menor numero de vuelo
	public void prioritizeFlights() {
		sort((a, b) -> {
			double occupancyA = a.getOccupancyPercentage();
			double occupancyB = b.getOccupancyPercentage();

			if (occupancyA != occupancyB) {
				return occupancyA > occupancyB ? -1 : 1;
			}
			return Integer.compare(a.getFlightNumber(), b.getFlightNumber());
		});
	}

	public Flight[] getAllFlights() {
		return toArray(new Flight[size()]);
	}

	public void print() {

		Flight[] all = getAllFlights();

		for (int i = 0; i < all.length; i++) {
			System.out.println("vuelo: " + all[i].getFlightNumber() + "ocupacion: " + all[i].getOccupancyPercentage());
		}
	}
}
