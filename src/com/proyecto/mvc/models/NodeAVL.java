package com.proyecto.mvc.models;

public class NodeAVL {

	private Flight flight;
	private NodeAVL left;
	private NodeAVL right;
	private int height;

	public NodeAVL(Flight flight) {
		this.flight = flight;
		this.left = null;
		this.right = null;
		this.height = 1;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public NodeAVL getLeft() {
		return left;
	}

	public void setLeft(NodeAVL left) {
		this.left = left;
	}

	public NodeAVL getRight() {
		return right;
	}

	public void setRight(NodeAVL right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getKey() {
		return flight.getFlightNumber();
	}
}
