package com.proyecto.mvc.models;

public class Node {

	private Node next;
	private Node previous;
	private Flight flight;
	
	public Node(Flight flight) {
		
		this.flight = flight;
		this.next = null;
		this.previous = null;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
	
}
