package com.proyecto.mvc.models;

public class EdgeNode {
	private Vertex destination;
	private EdgeNode next;
	private int weight;
	

	public EdgeNode(Vertex destination, int weight) {
		this.destination = destination;
		this.weight = weight;
		this.next = null;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public Vertex getDestination() {
		return destination;
	}


	public void setDestination(Vertex destination) {
		this.destination = destination;
	}


	public EdgeNode getNext() {
		return next;
	}


	public void setNext(EdgeNode next) {
		this.next = next;
	}
	
	

}
