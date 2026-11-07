package com.proyecto.mvc.models;

public class AVLTree {

	private NodeAVL root;
	private String log;

	public AVLTree() {
		this.root = null;
		this.log = "";
	}

	public NodeAVL getRoot() {
		return root;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public String getLog() {
		return log;
	}

	public int height(NodeAVL node) {
		if (node == null) {
			return 0;
		}
		return node.getHeight();
	}

	public int getBalance(NodeAVL node) {
		if (node == null) {
			return 0;
		}
		return height(node.getLeft()) - height(node.getRight());
	}

	private void updateHeight(NodeAVL node) {
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
	}

	public boolean insert(Flight flight) {

		if (search(flight.getFlightNumber()) != null) {
			return false;
		}

		log = "";
		root = insert(root, flight);
		return true;
	}

	private NodeAVL insert(NodeAVL node, Flight flight) {

		if (node == null) {
			return new NodeAVL(flight);
		}

		if (flight.getFlightNumber() < node.getKey()) {
			node.setLeft(insert(node.getLeft(), flight));
		} else {
			node.setRight(insert(node.getRight(), flight));
		}

		return balance(node);
	}

	public Flight search(int flightNumber) {

		NodeAVL aux = root;

		while (aux != null) {

			if (flightNumber == aux.getKey()) {
				return aux.getFlight();
			}

			if (flightNumber < aux.getKey()) {
				aux = aux.getLeft();
			} else {
				aux = aux.getRight();
			}
		}

		return null;
	}

	public boolean delete(int flightNumber) {

		if (search(flightNumber) == null) {
			return false;
		}

		log = "";
		root = delete(root, flightNumber);
		return true;
	}

	private NodeAVL delete(NodeAVL node, int flightNumber) {

		if (node == null) {
			return null;
		}

		if (flightNumber < node.getKey()) {
			node.setLeft(delete(node.getLeft(), flightNumber));

		} else if (flightNumber > node.getKey()) {
			node.setRight(delete(node.getRight(), flightNumber));

		} else {

			if (node.getLeft() == null) {
				return node.getRight();
			}
			if (node.getRight() == null) {
				return node.getLeft();
			}

			NodeAVL min = node.getRight();// sucesor
			while (min.getLeft() != null) {
				min = min.getLeft();
			}

			node.setFlight(min.getFlight());
			node.setRight(delete(node.getRight(), min.getKey()));
		}

		return balance(node);
	}

	private NodeAVL balance(NodeAVL node) {

		updateHeight(node);
		int balance = getBalance(node);

		if (balance > 1) {

			if (getBalance(node.getLeft()) >= 0) {
				log += "Desbalance en " + node.getKey() + " (FB " + balance + ") -> Rotacion simple a la derecha (RSD)\n";
				return rotateRight(node);
			}

			log += "Desbalance en " + node.getKey() + " (FB " + balance + ") -> Rotacion doble izquierda-derecha (RDD)\n";
			return rotateLeftRight(node);
		}

		if (balance < -1) {

			if (getBalance(node.getRight()) <= 0) {
				log += "Desbalance en " + node.getKey() + " (FB " + balance + ") -> Rotacion simple a la izquierda (RSI)\n";
				return rotateLeft(node);
			}

			log += "Desbalance en " + node.getKey() + " (FB " + balance + ") -> Rotacion doble derecha-izquierda (RDI)\n";
			return rotateRightLeft(node);
		}

		return node;
	}

	private NodeAVL rotateRight(NodeAVL node) {

		NodeAVL newRoot = node.getLeft();
		node.setLeft(newRoot.getRight());
		newRoot.setRight(node);

		updateHeight(node);
		updateHeight(newRoot);

		return newRoot;
	}

	private NodeAVL rotateLeft(NodeAVL node) {

		NodeAVL newRoot = node.getRight();
		node.setRight(newRoot.getLeft());
		newRoot.setLeft(node);

		updateHeight(node);
		updateHeight(newRoot);

		return newRoot;
	}

	private NodeAVL rotateLeftRight(NodeAVL node) {
		node.setLeft(rotateLeft(node.getLeft()));
		return rotateRight(node);
	}

	private NodeAVL rotateRightLeft(NodeAVL node) {
		node.setRight(rotateRight(node.getRight()));
		return rotateLeft(node);
	}

	public int size() {
		return size(root);
	}

	private int size(NodeAVL node) {
		if (node == null) {
			return 0;
		}
		return 1 + size(node.getLeft()) + size(node.getRight());
	}

	public String preorder() {
		return preorder(root).trim();
	}

	private String preorder(NodeAVL node) {
		if (node == null) {
			return "";
		}
		return node.getKey() + " " + preorder(node.getLeft()) + preorder(node.getRight());
	}

	public String inorder() {
		return inorder(root).trim();
	}

	private String inorder(NodeAVL node) {
		if (node == null) {
			return "";
		}
		return inorder(node.getLeft()) + node.getKey() + " " + inorder(node.getRight());
	}

	public String postorder() {
		return postorder(root).trim();
	}

	private String postorder(NodeAVL node) {
		if (node == null) {
			return "";
		}
		return postorder(node.getLeft()) + postorder(node.getRight()) + node.getKey() + " ";
	}
}