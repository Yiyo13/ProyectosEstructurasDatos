package com.proyecto.mvc.models;

// Lista doble de pasajeros (asientos de un vuelo).
// La estructura es DoubleList<Passenger>; aqui solo va lo propio de los asientos:
// la capacidad maxima, el orden alfabetico y el orden por edad.
public class DoubleListPassenger extends DoubleList<Passenger> {

	private int maxCapFlight;

	public DoubleListPassenger() {
		this(0);
	}

	public DoubleListPassenger(int maxCapFlight) {
		super();
		this.maxCapFlight = maxCapFlight;
	}

	// agrega en orden alfabetico por nombre, si hay campo
	public boolean addPassenger(Passenger passenger) {

		if (size() >= maxCapFlight) {
			return false;
		}

		addSorted(passenger, (a, b) -> a.getNameP().compareToIgnoreCase(b.getNameP()));
		return true;
	}

	public void sortByAge(boolean ascending) {

		if (ascending) {
			sort((a, b) -> Integer.compare(a.getAgeP(), b.getAgeP()));
		} else {
			sort((a, b) -> Integer.compare(b.getAgeP(), a.getAgeP()));
		}
	}

	public Passenger findById(int id) {
		return find(p -> p.getIdP() == id);
	}

	public void setMaxCapFlight(int maxCapFlight) {
		this.maxCapFlight = maxCapFlight;
	}

	public int getCurrentCapacity() {
		return size();
	}

	@Override
	public String toString() {

		if (isEmpty()) {
			return "No hay pasajeros registrados.";
		}

		String resultado = "";
		Node<Passenger> aux = getFirst();

		while (aux != null) {
			resultado += aux.getData().toString() + "\n";
			aux = aux.getNext();
		}

		return resultado;
	}

	// se deja la misma firma que en el Proyecto 1 para no romper a quien la llama
	public boolean checkDuplicatesPassenger(DoubleListPassenger passengers, int id) {
		return passengers.findById(id) != null;
	}
}
