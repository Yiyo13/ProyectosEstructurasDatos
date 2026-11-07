package com.proyecto.mvc.models;

public class Graph {
	private VertexNode start;

	public Graph() {
		this.start = null;
	}

	public boolean isEmpty() {
		return this.start == null;
	}

	public VertexNode getStart() {
		return start;
	}

	public boolean addVertex(String name) {
		if(name == null || name.trim().isEmpty() || findVertex(name) != null) {
			return false;
		}
		Vertex newVertex = new Vertex(name.trim());
		VertexNode newNode = new VertexNode(newVertex);
		if(this.start == null) {
			this.start = newNode;
		}else {
			VertexNode aux = this.start;
			while(aux.getNext() != null) {
				aux = aux.getNext();
			}
			aux.setNext(newNode);
		}
		return true;
	}

	public Vertex findVertex(String name) {
		VertexNode aux = this.start;
		while (aux != null) {
			if(aux.getVertex().getName().equalsIgnoreCase(name)) {
				return aux.getVertex();
			}
			aux = aux.getNext();
		}
		return null;
	}

	public boolean hasEdge(String origin, String destination) {
		Vertex originVertex = findVertex(origin);
		if(originVertex == null) {
			return false;
		}
		EdgeNode edge = originVertex.getAdjacencyList();
		while(edge != null) {
			if(edge.getDestination().getName().equalsIgnoreCase(destination)) {
				return true;
			}
			edge = edge.getNext();
		}
		return false;
	}

	public String addEdge(String origin, String destination, int weight) {
		Vertex originVertex = findVertex(origin);
		Vertex destinationVertex = findVertex(destination);

		if(originVertex == null || destinationVertex == null) {
			return "El aeropuerto no existe";
		}
		if(originVertex == destinationVertex) {
			return "El origen y el destino no pueden ser iguales";
		}
		if(weight <= 0) {
			return "La distancia debe ser mayor a 0";
		}
		if(hasEdge(origin, destination)) {
			return "La conexion ya existe";
		}

		originVertex.addAdjacencyVertex(destinationVertex, weight);
		destinationVertex.addAdjacencyVertex(originVertex, weight);// ida y vuelta
		return null;
	}

	public int countVertex() {
		int count = 0;
		VertexNode aux = this.start;
		while(aux != null) {
			count++;
			aux = aux.getNext();
		}
		return count;
	}

	public String[] getNames() {
		String[] names = new String[countVertex()];
		VertexNode aux = this.start;
		int i = 0;
		while(aux != null) {
			names[i++] = aux.getVertex().getName();
			aux = aux.getNext();
		}
		return names;
	}

	public String showConnections(String name) {
		Vertex vertex = findVertex(name);
		if(vertex == null) {
			return "No existe el aeropuerto " + name;
		}
		EdgeNode edge = vertex.getAdjacencyList();
		if(edge == null) {
			return name + " no tiene conexiones";
		}
		String text = "Conexiones de " + name + ":\n";
		while(edge != null) {
			text += "  " + edge.getDestination().getName() + "  " + edge.getWeight() + " km\n";
			edge = edge.getNext();
		}
		return text;
	}

	public void resetVertex() {
		VertexNode aux = this.start;
		while(aux != null) {
			Vertex vertex = aux.getVertex();
			vertex.setDistance(Integer.MAX_VALUE);
			vertex.setVisited(false);
			vertex.setLast(null);
			aux = aux.getNext();
		}
	}

	public String dfs(String origin) {
		Vertex originVertex = findVertex(origin);
		if(originVertex == null) {
			return null;
		}
		resetVertex();
		StringBuilder order = new StringBuilder();
		dfs(originVertex, order);
		return order.toString();
	}

	private void dfs(Vertex vertex, StringBuilder order) {
		vertex.setVisited(true);
		if(order.length() > 0) {
			order.append(" -> ");
		}
		order.append(vertex.getName());

		EdgeNode edge = vertex.getAdjacencyList();
		while(edge != null) {
			if(!edge.getDestination().isVisited()) {
				dfs(edge.getDestination(), order);// baja profundo
			}
			edge = edge.getNext();
		}
	}

	public boolean hasRoute(String origin, String destination) {
		Vertex destinationVertex = findVertex(destination);
		if(destinationVertex == null || dfs(origin) == null) {
			return false;
		}
		return destinationVertex.isVisited();
	}

	public Vertex getVertexMinDistanceNotVisited() {
		VertexNode aux = this.start;
		Vertex minVertex = null;
		while(aux != null) {
			Vertex temp = aux.getVertex();
			if(temp.isVisited()== false) {
				if(minVertex == null || minVertex.getDistance()> temp.getDistance()) {
					minVertex = temp;
				}
			}
			aux = aux.getNext();
		}
		return minVertex;
	}

	public String dijkstra(String origin) {
		Vertex originVertex = findVertex(origin);
		if(originVertex == null) {
			return null;
		}

		resetVertex();
		originVertex.setDistance(0);

		StringBuilder steps = new StringBuilder();
		int step = 1;

		Vertex current = getVertexMinDistanceNotVisited();
		while(current != null && current.getDistance() != Integer.MAX_VALUE) {
			steps.append("Paso ").append(step++).append(": se procesa ").append(current.getName())
				.append(" (").append(current.getDistance()).append(" km)\n");

			EdgeNode edgeNodes = current.getAdjacencyList();
			while(edgeNodes != null) {
				Vertex destination = edgeNodes.getDestination();

				if(!destination.isVisited()) {
					int newDistance = current.getDistance()+edgeNodes.getWeight();
					if(newDistance < destination.getDistance()) {
						steps.append("   ").append(destination.getName()).append(": ")
							.append(showDistance(destination)).append(" -> ").append(newDistance)
							.append(" km, predecesor ").append(current.getName()).append("\n");
						destination.setDistance(newDistance);
						destination.setLast(current);
					}
				}
				edgeNodes = edgeNodes.getNext();
			}
			current.setVisited(true);
			steps.append(showPending()).append("\n");
			current = getVertexMinDistanceNotVisited();
		}
		steps.append(showTable());
		return steps.toString();
	}

	private String showDistance(Vertex vertex) {
		if(vertex.getDistance() == Integer.MAX_VALUE) {
			return "inf";
		}
		return vertex.getDistance() + " km";
	}

	private String showPending() {// tentativas sin visitar
		String text = "";
		VertexNode aux = this.start;
		while(aux != null) {
			Vertex vertex = aux.getVertex();
			if(!vertex.isVisited() && vertex.getDistance() != Integer.MAX_VALUE) {
				text += (text.isEmpty() ? "" : ", ") + vertex.getName() + " " + vertex.getDistance();
			}
			aux = aux.getNext();
		}
		return "   Tentativas: " + (text.isEmpty() ? "ninguna" : text) + "\n";
	}

	private String showTable() {
		String text = "Tabla final (distancia | predecesor):\n";
		VertexNode aux = this.start;
		while(aux != null) {
			Vertex vertex = aux.getVertex();
			text += "   " + vertex.getName() + ": " + showDistance(vertex) + " | "
					+ (vertex.getLast() == null ? "-" : vertex.getLast().getName()) + "\n";
			aux = aux.getNext();
		}
		return text;
	}

	public String showTrace(String destination) {
		Vertex destinationVertex = findVertex(destination);
		if(destinationVertex == null) {
			return "No se encontro el destino " + destination;
		}
		if(destinationVertex.getDistance() == Integer.MAX_VALUE) {
			return "No existe ruta hacia " + destination;
		}
		return "Ruta: " + processTrace(destinationVertex) + "\nDistancia total: " + destinationVertex.getDistance() + " km";
	}

	private String processTrace(Vertex destinationVertex) {
		if(destinationVertex.getLast() != null) {
			return processTrace(destinationVertex.getLast()) + " -> " + destinationVertex.getName();
		}
		return destinationVertex.getName();
	}
}
