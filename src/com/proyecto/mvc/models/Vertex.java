package com.proyecto.mvc.models;

public class Vertex {
	private String name;
	private EdgeNode adjacencylist;//lista de adyacencia
	private boolean isVisited;
	private int distance;
	private Vertex last;
	
	public Vertex(String name) {
		this.name = name;
		this.adjacencylist = null;
		this.distance = Integer.MAX_VALUE;
		this.isVisited = false;
		this.last = null;
	}
	
	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Vertex getLast() {
		return last;
	}

	public void setLast(Vertex last) {
		this.last = last;
	}

	public String getName() {
		return this.name;
	}
	public EdgeNode getAdjacencyList() {
		return this.adjacencylist;
	}
	
	public void addAdjacencyVertex(Vertex destination, int weight) {
		EdgeNode edge = new EdgeNode(destination, weight);
		if(this.adjacencylist == null) {
			this.adjacencylist = edge;
		}else {
			EdgeNode aux = this.adjacencylist;
			while(aux.getNext() != null) {
				aux = aux.getNext();
			}
			aux.setNext(edge);
		}
	}
	
	
	
}
