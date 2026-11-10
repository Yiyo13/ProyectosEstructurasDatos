package com.proyecto.mvc.models;

// Cola generica (FIFO): el primero que entra es el primero que sale.
// Se usa como Queue<Passenger> para la cola de abordaje de cada vuelo.
public class Queue<T> {

	private Node<T> first;
	private Node<T> last;
	private int size;

	public Queue() {
		first = null;
		last = null;
		size = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public Node<T> getFirst() {// para recorrerla sin sacar elementos
		return first;
	}

	public void enqueue(T data) {

		Node<T> node = new Node<>(data);

		if (isEmpty()) {
			first = node;
		} else {
			last.setNext(node);
		}
		last = node;
		size++;
	}

	public T dequeue() {

		if (isEmpty()) return null;

		T data = first.getData();
		first = first.getNext();

		if (first == null) {
			last = null;
		}
		size--;

		return data;
	}

	public T peek() {
		return isEmpty() ? null : first.getData();
	}
}
