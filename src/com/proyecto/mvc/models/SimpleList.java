package com.proyecto.mvc.models;

// Lista simplemente enlazada generica: head -> ... -> null.
// Compara por referencia (==), o sea, guarda los MISMOS objetos que estan en el
// registro general; no hace copias. Vertex la usa (via FlightList) para
// los vuelos en transito, pendientes y aterrizados de cada aeropuerto.
public class SimpleList<T> {

	private Node<T> head;
	private int size;

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return size;
	}

	public Node<T> getHead() {
		return head;
	}

	public void add(T data) {

		Node<T> node = new Node<>(data);

		if (head == null) {
			head = node;
		} else {
			Node<T> aux = head;
			while (aux.getNext() != null) {
				aux = aux.getNext();
			}
			aux.setNext(node);
		}
		size++;
	}

	public boolean remove(T data) {

		if (head == null) return false;

		if (head.getData() == data) {
			head = head.getNext();
			size--;
			return true;
		}

		Node<T> aux = head;
		while (aux.getNext() != null) {
			if (aux.getNext().getData() == data) {
				aux.setNext(aux.getNext().getNext());
				size--;
				return true;
			}
			aux = aux.getNext();
		}
		return false;
	}

	public boolean contains(T data) {

		Node<T> aux = head;
		while (aux != null) {
			if (aux.getData() == data) return true;
			aux = aux.getNext();
		}
		return false;
	}

	public void clear() {
		head = null;
		size = 0;
	}

	// llena el arreglo recibido (en Java no se puede hacer "new T[n]")
	public T[] toArray(T[] result) {

		Node<T> aux = head;
		for (int i = 0; i < result.length && aux != null; i++) {
			result[i] = aux.getData();
			aux = aux.getNext();
		}
		return result;
	}
}
