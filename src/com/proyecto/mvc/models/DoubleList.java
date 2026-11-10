package com.proyecto.mvc.models;

import java.util.Comparator;
import java.util.function.Predicate;

// Lista doblemente enlazada generica (no circular): first <-> ... <-> last.
// Tiene un "current" para navegar con anterior/siguiente.
// DoubleListPassenger la usa con T = Passenger.
public class DoubleList<T> {

	private Node<T> first;
	private Node<T> last;
	private Node<T> current;
	private int size;

	public DoubleList() {
		this.first = null;
		this.last = null;
		this.current = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.first == null;
	}

	public int size() {
		return size;
	}

	public Node<T> getFirst() {
		return first;
	}

	public Node<T> getLast() {
		return last;
	}

	// inserta manteniendo el orden: queda antes del primer elemento que sea "mayor" segun goesFirst
	public void addSorted(T data, Comparator<T> goesFirst) {

		Node<T> node = new Node<>(data);

		if (isEmpty()) {
			first = node;
			last = node;
			current = node;
			size++;
			return;
		}

		Node<T> aux = first;
		while (aux != null && goesFirst.compare(aux.getData(), data) < 0) {
			aux = aux.getNext();
		}

		if (aux == null) {// va al final
			node.setPrevious(last);
			last.setNext(node);
			last = node;
		} else if (aux == first) {// va al inicio
			node.setNext(first);
			first.setPrevious(node);
			first = node;
		} else {// va en medio, antes de aux
			Node<T> previous = aux.getPrevious();
			node.setPrevious(previous);
			node.setNext(aux);
			previous.setNext(node);
			aux.setPrevious(node);
		}
		size++;
	}

	public T find(Predicate<T> condition) {

		Node<T> aux = first;

		while (aux != null) {
			if (condition.test(aux.getData())) {
				return aux.getData();
			}
			aux = aux.getNext();
		}
		return null;
	}

	public boolean contains(Predicate<T> condition) {
		return find(condition) != null;
	}

	// ordena intercambiando los datos de los nodos (mismo metodo que tenia sortByAge)
	public void sort(Comparator<T> goesFirst) {

		for (Node<T> i = first; i != null; i = i.getNext()) {
			for (Node<T> j = i.getNext(); j != null; j = j.getNext()) {
				if (goesFirst.compare(i.getData(), j.getData()) > 0) {
					swap(i, j);
				}
			}
		}
	}

	private void swap(Node<T> a, Node<T> b) {
		T temp = a.getData();
		a.setData(b.getData());
		b.setData(temp);
	}

	public T getPrevious() {

		if (current == null) return null;

		current = current.getPrevious();
		return current == null ? null : current.getData();
	}

	public T getNext() {

		if (current == null) return null;

		current = current.getNext();
		return current == null ? null : current.getData();
	}

	public T getCurrent() {

		if (current == null) return null;

		return current.getData();
	}
}
