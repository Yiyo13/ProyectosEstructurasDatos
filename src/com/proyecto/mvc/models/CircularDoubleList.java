package com.proyecto.mvc.models;

public class CircularDoubleList {
	
	private Node head;
	private Node current;//nodo actual
	
	
	
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
			this.current = node;
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
	
	public boolean checkDuplicateFlightNumber(String flightNumber) {
		
		boolean flag = false;
		
		if(!isEmpty()) {
			
			Node aux = this.head;
			
			do {
				
				if(aux.getFlight().getFlightNumber() == flightNumber) {
					flag = true;
				}
				
				aux = aux.getNext();
				
			} while (aux != this.head);
			
		}
		
		return flag;
	}
	
	public Flight getNext() {// se va moviendo hacia adelante cada que se llame este metodo
		
		if(current == null) return null;
		
		current = current.getNext();
		
		return current.getFlight();
	}
	
	public Flight getPrevious() {//se va moviendo hacia atras cada que se llame este metodo
		
		if(current == null) return null;
		
		current = current.getPrevious();
		
		return current.getFlight();
	}
	
	public Flight getCurrent() {//se obtiene el nodo actual, para que cuando se inicia el programa salga algun nodo
		
		if(current == null) return null;
		
		return current.getFlight();
	}
	
	
	public void quickSort() {
		
	}
}
