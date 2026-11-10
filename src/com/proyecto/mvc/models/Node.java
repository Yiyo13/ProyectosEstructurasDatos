package com.proyecto.mvc.models;

// Nodo generico: sirve para cualquier tipo de dato (Flight, Passenger, Trip...).
// T es el tipo del dato que guarda el nodo, se decide al crearlo: new Node<Flight>(vuelo).
// Las estructuras simples (pila, cola, lista simple) solo usan "next";
// las dobles (lista doble y circular doble) usan "next" y "previous".
public class Node<T> {

	private T data;
	private Node<T> next;
	private Node<T> previous;

	public Node(T data) {
		this.data = data;
		this.next = null;
		this.previous = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}
}
