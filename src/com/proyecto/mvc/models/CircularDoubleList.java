package com.proyecto.mvc.models;

public class CircularDoubleList {
	
	private Node head;
	
	
	
	
	public CircularDoubleList() {
		this.head = null;
		
	}

	public  boolean isEmpty() {
		return this.head==null;
	}

	public void add(Flight flight) {
		
		Node node = new Node(flight);
		
		if(isEmpty()) {
			
			this.head = node;
			this.head.setPrevious(node);
			this.head.setNext(node);	
		}else {
			
			Node aux = this.head;
			
			while(aux.getNext() != this.head) {//se pregunta si el siguiente de donde apunta aux es diferente a la cabeza
				
				aux = aux.getNext();
			}
			
			aux.setNext(node);
			node.setPrevious(aux);
			node.setNext(this.head);
			
			this.head.setPrevious(node);
		}
		
	}
	
}
