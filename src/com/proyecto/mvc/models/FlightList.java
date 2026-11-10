package com.proyecto.mvc.models;

// Lista simple de vuelos de un aeropuerto (en transito, pendientes, aterrizados).
// Toda la logica esta en SimpleList<Flight>; aqui solo se arma el arreglo tipado.
public class FlightList extends SimpleList<Flight> {

	public Flight[] toArray() {
		return toArray(new Flight[size()]);
	}
}
