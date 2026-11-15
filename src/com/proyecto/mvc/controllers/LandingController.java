package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightLogic;
import com.proyecto.mvc.models.Graph;
import com.proyecto.mvc.models.SimulationLogic;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.landings.LandingIndex;

public class LandingController {

	private ViewPrincipal vp;
	private FlightLogic flightLogic;
	private Graph graph;
	private SimulationLogic simulationLogic;

	private String airport;
	private Flight[] ordered;
	private String[] steps;
	private int shown;

	public LandingController(ViewPrincipal vp, FlightLogic flightLogic, Graph graph, SimulationLogic simulationLogic) {
		this.vp = vp;
		this.flightLogic = flightLogic;
		this.graph = graph;
		this.simulationLogic = simulationLogic;
	}

	// corre el Greedy y deja las iteraciones listas; false si no hay nada que priorizar
	private boolean prepare(LandingIndex v) {

		airport = (String) v.cbAirport.getSelectedItem();

		if (airport == null) {
			JOptionPane.showMessageDialog(null, "No hay aeropuertos registrados");
			return false;
		}

		Flight[] pending = graph.findVertex(airport).getPending().toArray();// pendientes de la lista propia del aeropuerto

		if (pending.length == 0) {
			steps = null;
			v.textArea.setText("No hay vuelos pendientes de aterrizaje en " + airport);
			return false;
		}

		StringBuilder trace = new StringBuilder();
		ordered = flightLogic.sortLandingPriority(pending, trace);
		steps = trace.toString().split("\n\n");// cada iteracion es un bloque
		shown = 0;
		return true;
	}

	private String header() {
		return "GREEDY - Aeropuerto " + airport + "\n"
				+ "Criterio: menor combustible primero; si empatan, menor numero de vuelo\n\n";
	}

	private String finalOrder() {

		String text = "=== ORDEN FINAL DE ATERRIZAJE ===\n";

		for (int i = 0; i < ordered.length; i++) {
			text += (i + 1) + ". Vuelo " + ordered[i].getFlightNumber() + " - " + ordered[i].getFuel() + " min\n";
		}
		return text;
	}

	public void index() {

		LandingIndex v = new LandingIndex();

		String[] names = graph.getNames();
		for (int i = 0; i < names.length; i++) {
			v.cbAirport.addItem(names[i]);
		}

		v.cbAirport.addActionListener(e -> {
			steps = null;
			v.textArea.setText("");
		});

		// Priorizar: muestra todas las iteraciones de una vez
		v.btnExecute.addActionListener(e -> {

			if (!prepare(v)) {
				return;
			}

			String text = header();
			for (int i = 0; i < steps.length; i++) {
				text += steps[i] + "\n\n";
			}
			shown = steps.length;

			v.textArea.setText(text + finalOrder());
			v.textArea.setCaretPosition(0);
		});

		// Paso a paso: cada clic agrega una iteracion
		v.btnStep.addActionListener(e -> {

			if (steps == null || shown >= steps.length) {// empezar de nuevo
				if (!prepare(v)) {
					return;
				}
				v.textArea.setText(header());
			}

			v.textArea.append(steps[shown++] + "\n\n");

			if (shown == steps.length) {
				v.textArea.append(finalOrder());
			}

			v.textArea.setCaretPosition(v.textArea.getDocument().getLength());
		});

		// Autorizar: aterriza solo el primero de la prioridad
		v.btnAuthorize.addActionListener(e -> {

			String selected = (String) v.cbAirport.getSelectedItem();

			if (selected == null) {
				return;
			}

			Flight[] pending = graph.findVertex(selected).getPending().toArray();// pendientes de la lista propia del aeropuerto

			if (pending.length == 0) {
				JOptionPane.showMessageDialog(null, "No hay vuelos pendientes en " + selected);
				return;
			}

			Flight first = flightLogic.sortLandingPriority(pending, new StringBuilder())[0];

			if (simulationLogic.authorizeLanding(first)) {
				flightLogic.save();
				steps = null;
				v.textArea.setText("Aterrizaje autorizado: vuelo " + first.getFlightNumber() + " ("
						+ first.getFuel() + " min). Estado: " + first.isStatus() + "\n\n"
						+ "Quedan " + (pending.length - 1) + " pendiente(s) en " + selected
						+ ". Presione Priorizar para recalcular.");
			}
		});

		vp.setContent(v, "Prioridad de aterrizaje");
	}
}