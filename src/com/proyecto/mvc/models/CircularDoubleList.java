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
	
	public boolean checkDuplicateFlightNumber(int flightNumber) {
		
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
	
	public boolean selectFlight(int flightNumber) {// mueve "current" directo al vuelo indicado
		
		if(isEmpty()) return false;
		
		Node aux = this.head;
		
		do {
			
			if(aux.getFlight().getFlightNumber() == flightNumber) {
				this.current = aux;
				return true;
			}
			
			aux = aux.getNext();
			
		} while (aux != this.head);
		
		return false;
	}
	
	
	public void prioritizeFlights() {
		
		if(isEmpty()) return;
		
		Node last = this.head.getPrevious();//se obtiene el ultimo nodo de la lista
		quickSort(this.head, last);// se le envia la cabeza y el ultimo nodo
		
	}

	public void quickSort(Node low, Node high) {
		
		if(high != null && low != high && low != high.getNext()) {
			
			Node pivot = partition(low,high);
			
			quickSort(low, pivot.getPrevious());
			quickSort(pivot.getNext(), high);
		}
		
		
	}

	private Node partition(Node low, Node high) {
		
		Flight pivot = high.getFlight();
		Node i = low.getPrevious();
		
		for(Node j = low; j!=high; j = j.getNext()) {
		
			if(compare(j.getFlight(), pivot)) {
				
				if(i == null) {
					i = low;
				}else {
					i = i.getNext();
				}
				
				swap(i ,j);
			}
			
		}
		
		if(i == null) {
			i = low;
		}else {
			i = i.getNext();
		}
		
		swap(i ,high);
		
		return i;
	}
	
	private boolean compare(Flight a, Flight b) {
		
		double occupancyA = a.getOccupancyPercentage();
		double occupancyB = b.getOccupancyPercentage();
		
		if(occupancyA != occupancyB) {
			return occupancyA > occupancyB;
		}
		
		return a.getFlightNumber() < b.getFlightNumber();
	}
	
	private void swap(Node a, Node b) {
		
		Flight temp = a.getFlight();
		a.setFlight(b.getFlight());
		b.setFlight(temp);
	}
	public Flight[] getAllFlights() {

	    if (isEmpty()) {
	        return new Flight[0];
	    }

	    int count = 0;
	    Node aux = this.head;
	    do {
	        count++;
	        aux = aux.getNext();
	    } while (aux != this.head);

	    
	    Flight[] result = new Flight[count];
	    aux = this.head;
	    for (int i = 0; i < count; i++) {
	        result[i] = aux.getFlight();
	        aux = aux.getNext();
	    }

	    return result;
	}
	
}