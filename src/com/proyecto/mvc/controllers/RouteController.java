package com.proyecto.mvc.controllers;

import com.proyecto.mvc.models.Graph;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.routes.RouteIndex;

public class RouteController {

	private ViewPrincipal vp;
	private Graph graph;

	public RouteController(ViewPrincipal vp, Graph graph) {
		this.vp = vp;
		this.graph = graph;
	}

	public void index() {

		RouteIndex v = new RouteIndex();
		String[] names = graph.getNames();

		for (int i = 0; i < names.length; i++) {
			v.cbOrigin.addItem(names[i]);
			v.cbDestination.addItem(names[i]);
		}

		v.btnDfs.addActionListener(e -> {

			String origin = (String) v.cbOrigin.getSelectedItem();
			String destination = (String) v.cbDestination.getSelectedItem();

			if (origin == null || destination == null) {
				v.textArea.setText("No hay aeropuertos registrados");
				return;
			}

			String order = graph.dfs(origin);
			boolean route = graph.hasRoute(origin, destination);

			v.textArea.setText("DFS desde " + origin + "\n\n"
					+ "Orden de visita:\n" + order + "\n\n"
					+ (route ? "Si existe ruta de " : "No existe ruta de ") + origin + " a " + destination);
		});

		v.btnDijkstra.addActionListener(e -> {

			String origin = (String) v.cbOrigin.getSelectedItem();
			String destination = (String) v.cbDestination.getSelectedItem();

			if (origin == null || destination == null) {
				v.textArea.setText("No hay aeropuertos registrados");
				return;
			}

			String steps = graph.dijkstra(origin);

			v.textArea.setText("Dijkstra de " + origin + " a " + destination + "\n\n"
					+ graph.showTrace(destination) + "\n\n"
					+ "Paso a paso:\n" + steps);
			v.textArea.setCaretPosition(0);
		});

		vp.setContent(v, "Rutas entre Aeropuertos");
	}
}
