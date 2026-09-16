package com.proyecto.mvc.models;

public class Main {

	public static void main(String[] args) {


		DoubleListPassenger lista = new DoubleListPassenger(3);

		// Insertamos pasajeros en desorden a propósito, para ver si los ordena bien
		Passenger p1 = new Passenger(101, "Xinia Mora", 30);
		Passenger p2 = new Passenger(102, "Ana Rodriguez", 25);
		Passenger p3 = new Passenger(103, "Bruno Castro", 40);
		Passenger p4 = new Passenger(104, "Diego Vargas", 22); // este debería fallar (capacidad = 3)

		System.out.println("Insertando a Carlos: " + lista.addPassenger(p1));
		System.out.println("Insertando a Ana: " + lista.addPassenger(p2));
		System.out.println("Insertando a Bruno: " + lista.addPassenger(p3));
		System.out.println("Insertando a Diego (debería fallar): " + lista.addPassenger(p4));

		System.out.println("\n--- Recorrido de inicio a fin ---");
		NodePassenger aux = lista.getFirst();
		while (aux != null) {
			System.out.println(aux.getPassenger().toString());
			aux = aux.getNext();
		}

		System.out.println("\n--- Recorrido de fin a inicio ---");
		aux = lista.getLast();
		while (aux != null) {
			System.out.println(aux.getPassenger().toString());
			aux = aux.getPrev();
		}
	}

}
