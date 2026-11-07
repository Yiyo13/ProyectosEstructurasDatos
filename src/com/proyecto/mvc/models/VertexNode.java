package com.proyecto.mvc.models;

public class VertexNode {
	private Vertex vertex;
	private VertexNode next;
	
	public VertexNode(Vertex vertex) {
		this.vertex = vertex;
		this.next = null;
	}

	public Vertex getVertex() {
		//
		return this.vertex;
	}

	public VertexNode getNext() {
		return next;
	}

	public void setNext(VertexNode next) {
		this.next = next;
	}

	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}
	

}
