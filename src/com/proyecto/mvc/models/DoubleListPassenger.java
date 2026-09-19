package com.proyecto.mvc.models;

public class DoubleListPassenger {
	private NodePassenger first;
	private NodePassenger last;
	private int maxCapFlight;
	private int currentCap;
	private NodePassenger current;
	
	public DoubleListPassenger() {
		
	}
	
	public DoubleListPassenger( int maxCapFlight) {

		this.first = null;
		this.last = null;
		this.maxCapFlight = maxCapFlight;
		this.currentCap = 0;
	}
	public boolean isEmpty() {
		return this.first == null;
	}

	public boolean addPassenger(Passenger passenger) {
		if(currentCap >= maxCapFlight) {
			return false;
		}
		NodePassenger node = new NodePassenger(passenger);
		if(isEmpty()) {
			first = node;
			last = node;
			currentCap++;
			this.current = node;
			return true;

		}
		NodePassenger aux = first;
		while(aux != null && aux.getPassenger().getNameP()
				.compareToIgnoreCase(passenger.getNameP())<0){
			aux = aux.getNext();
		}
		if(aux == null) {
			node.setPrev(last);
			last.setNext(node);
			last = node;
		}else if(aux == first) {
			node.setNext(first);
			first.setPrev(node);
			first = node;
			
		}else {
			NodePassenger previous = aux.getPrev();
			node.setPrev(previous);
			node.setNext(aux);
			previous.setNext(node);
			aux.setPrev(node);
			
			
		}
		currentCap++;

		return true;
	}
	
	public void sortByAge(boolean ascending) {

	    if (isEmpty()) {
	        return;
	    }

	    for (NodePassenger i = first; i != null; i = i.getNext()) {

	        for (NodePassenger j = i.getNext(); j != null; j = j.getNext()) {

	            boolean shouldSwap;

	            if (ascending) {
	                shouldSwap = i.getPassenger().getAgeP() > j.getPassenger().getAgeP();
	            } else {
	                shouldSwap = i.getPassenger().getAgeP() < j.getPassenger().getAgeP();
	            }

	            if (shouldSwap) {
	                swap(i, j);
	            }
	        }
	    }
	}
	private void swap(NodePassenger a, NodePassenger b) {

	    Passenger temp = a.getPassenger();
	    a.setPassenger(b.getPassenger());
	    b.setPassenger(temp);
	}
	
	public NodePassenger getFirst() {
	    return first;
	}

	public NodePassenger getLast() {
	    return last;
	}
	
	public Passenger getPrevious() {

		if(current == null) return null;

		current = current.getPrev();
		return current.getPassenger();
	}
	
	public Passenger getNext() {//
		
		if(current == null) return null;
		
		current = current.getNext();
		return current.getPassenger();
	}
	
	public Passenger getCurrent() {
		
		if(current == null) return null;
		
		
		return current.getPassenger();
	}
	
	public int getCurrentCapacity() {
		return this.currentCap;
	}

	@Override
	public String toString() {

		if (isEmpty()) {
			return "No hay pasajeros registrados.";
		}

		String resultado = "";
		NodePassenger aux = first;

		while (aux != null) {
			resultado += aux.getPassenger().toString() + "\n";
			aux = aux.getNext();
		}

		return resultado;
	}

}