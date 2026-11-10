package com.proyecto.mvc.models;

import java.util.Comparator;
import java.util.function.Predicate;

// Lista doblemente enlazada circular generica.
// No sabe nada de vuelos: para buscar recibe un Predicate (una condicion)
// y para ordenar recibe un Comparator (como comparar dos elementos).
// FlightCircularList la usa con T = Flight.
public class CircularDoubleList<T> {

	private Node<T> head;
	private Node<T> current;// nodo actual (para navegar con anterior/siguiente)
	private int size;

	public CircularDoubleList() {
		this.head = null;
		this.current = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public int size() {
		return size;
	}

	public Node<T> getHead() {
		return head;
	}

	// agrega al final (antes de la cabeza, porque es circular)
	public void add(T data) {

		Node<T> node = new Node<>(data);

		if (isEmpty()) {
			this.head = node;
			this.head.setPrevious(node);
			this.head.setNext(node);
			this.current = node;
		} else {
			Node<T> last = this.head.getPrevious();// en una circular doble el ultimo es head.previous

			last.setNext(node);
			node.setPrevious(last);
			node.setNext(this.head);
			this.head.setPrevious(node);
		}
		size++;
	}

	// devuelve el nodo del primer elemento que cumpla la condicion, o null
	private Node<T> findNode(Predicate<T> condition) {

		if (isEmpty()) return null;

		Node<T> aux = this.head;

		do {
			if (condition.test(aux.getData())) {
				return aux;
			}
			aux = aux.getNext();
		} while (aux != this.head);

		return null;
	}

	public T find(Predicate<T> condition) {
		Node<T> node = findNode(condition);
		return node == null ? null : node.getData();
	}

	public boolean contains(Predicate<T> condition) {
		return findNode(condition) != null;
	}

	// elimina el primer elemento que cumpla la condicion
	public boolean remove(Predicate<T> condition) {

		Node<T> aux = findNode(condition);

		if (aux == null) return false;

		if (aux.getNext() == aux) {// solo habia uno
			this.head = null;
			this.current = null;
			size = 0;
			return true;
		}

		aux.getPrevious().setNext(aux.getNext());
		aux.getNext().setPrevious(aux.getPrevious());

		if (aux == this.head) {
			this.head = aux.getNext();
		}
		if (aux == this.current) {
			this.current = aux.getNext();
		}
		size--;
		return true;
	}

	// mueve "current" directo al elemento que cumpla la condicion
	public boolean select(Predicate<T> condition) {

		Node<T> node = findNode(condition);

		if (node == null) return false;

		this.current = node;
		return true;
	}

	public T getNext() {// avanza "current" y devuelve el dato

		if (current == null) return null;

		current = current.getNext();
		return current.getData();
	}

	public T getPrevious() {// retrocede "current" y devuelve el dato

		if (current == null) return null;

		current = current.getPrevious();
		return current.getData();
	}

	public T getCurrent() {

		if (current == null) return null;

		return current.getData();
	}

	// llena el arreglo recibido con los elementos, desde la cabeza.
	// Se pide el arreglo afuera porque en Java no se puede hacer "new T[n]".
	public T[] toArray(T[] result) {

		if (isEmpty()) return result;

		Node<T> aux = this.head;
		for (int i = 0; i < result.length && i < size; i++) {
			result[i] = aux.getData();
			aux = aux.getNext();
		}
		return result;
	}

	// ---------------- QuickSort sobre los nodos ----------------
	// "goesFirst" decide el orden: si goesFirst.compare(a, b) < 0, a va antes que b.

	public void sort(Comparator<T> goesFirst) {

		if (isEmpty()) return;

		// se "abre" el circulo para que el QuickSort trabaje como en una lista doble normal
		Node<T> last = this.head.getPrevious();

		last.setNext(null);
		this.head.setPrevious(null);

		quickSort(this.head, last, goesFirst);

		// se vuelve a cerrar el circulo
		this.head.setPrevious(last);
		last.setNext(this.head);
	}

	private void quickSort(Node<T> low, Node<T> high, Comparator<T> goesFirst) {

		if (high != null && low != high && low != high.getNext()) {

			Node<T> pivot = partition(low, high, goesFirst);

			quickSort(low, pivot.getPrevious(), goesFirst);
			quickSort(pivot.getNext(), high, goesFirst);
		}
	}

	private Node<T> partition(Node<T> low, Node<T> high, Comparator<T> goesFirst) {

		T pivot = high.getData();
		Node<T> i = low.getPrevious();

		for (Node<T> j = low; j != high; j = j.getNext()) {

			if (goesFirst.compare(j.getData(), pivot) < 0) {

				i = (i == null) ? low : i.getNext();
				swap(i, j);
			}
		}

		i = (i == null) ? low : i.getNext();
		swap(i, high);

		return i;
	}

	private void swap(Node<T> a, Node<T> b) {// se intercambian los datos, no los nodos
		T temp = a.getData();
		a.setData(b.getData());
		b.setData(temp);
	}
}
