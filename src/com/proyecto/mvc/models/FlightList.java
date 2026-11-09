package com.proyecto.mvc.models;

public class FlightList {

	private Node head;

	public boolean isEmpty() {
		return head == null;
	}

	public void add(Flight flight) {

		Node node = new Node(flight);

		if (head == null) {
			head = node;
			return;
		}

		Node aux = head;
		while (aux.getNext() != null) {
			aux = aux.getNext();
		}
		aux.setNext(node);
	}

	public boolean remove(Flight flight) {

		if (head == null) return false;

		if (head.getFlight() == flight) {
			head = head.getNext();
			return true;
		}

		Node aux = head;
		while (aux.getNext() != null) {
			if (aux.getNext().getFlight() == flight) {
				aux.setNext(aux.getNext().getNext());
				return true;
			}
			aux = aux.getNext();
		}
		return false;
	}

	public boolean contains(Flight flight) {

		Node aux = head;
		while (aux != null) {
			if (aux.getFlight() == flight) return true;
			aux = aux.getNext();
		}
		return false;
	}

	public void clear() {
		head = null;
	}

	public Flight[] toArray() {

		int count = 0;
		Node aux = head;
		while (aux != null) {
			count++;
			aux = aux.getNext();
		}

		Flight[] result = new Flight[count];
		aux = head;
		for (int i = 0; i < count; i++) {
			result[i] = aux.getFlight();
			aux = aux.getNext();
		}
		return result;
	}
}