package com.proyecto.mvc.models;

public class NodePassenger {
	private Passenger passenger;
	private NodePassenger prev;
	private NodePassenger next;
	
	public NodePassenger(Passenger passenger) {
		
		this.passenger = passenger;
	
	}

	public Passenger getPassenger() {
		return passenger;
	}
	
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public NodePassenger getPrev() {
		return prev;
	}
	public void setPrev(NodePassenger prev) {
		this.prev = prev;
	}
	public NodePassenger getNext() {
		return next;
	}
	public void setNext(NodePassenger next) {
		this.next = next;
	}

	
	

}
