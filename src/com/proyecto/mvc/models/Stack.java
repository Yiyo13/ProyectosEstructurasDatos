package com.proyecto.mvc.models;

// Pila generica (LIFO): el ultimo que entra es el primero que sale.
// Se usa como Stack<Trip> para "Mis viajes".
public class Stack<T> {

	private Node<T> top;
	private int size;

	public Stack() {
		top = null;
		size = 0;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public int size() {
		return size;
	}

	public Node<T> getTop() {// para recorrerla sin sacar elementos
		return top;
	}

	public void push(T data) {

		Node<T> node = new Node<>(data);

		node.setNext(top);
		top = node;
		size++;
	}

	public T pop() {

		if (isEmpty()) return null;

		T data = top.getData();
		top = top.getNext();
		size--;

		return data;
	}

	public T peek() {
		return isEmpty() ? null : top.getData();
	}
}
