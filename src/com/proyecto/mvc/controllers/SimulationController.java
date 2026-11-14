package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightLogic;
import com.proyecto.mvc.models.Graph;
import com.proyecto.mvc.models.SimulationLogic;
import com.proyecto.mvc.models.Vertex;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.simulation.SimulationIndex;

public class SimulationController {

	private ViewPrincipal vp;
	private SimulationLogic logic;
	private Graph graph;
	private FlightLogic flightLogic;
	private Timer timer;
	private SimulationIndex v;

	public SimulationController(ViewPrincipal vp, SimulationLogic logic, Graph graph, FlightLogic flightLogic) {
		this.vp = vp;
		this.logic = logic;
		this.graph = graph;
		this.flightLogic = flightLogic;
		this.timer = new Timer(1000, e -> onTick());
	}

	private void onTick() {

		boolean active = logic.tick();
		refresh();

		if (!active) {
			timer.stop();
			flightLogic.save();
			if (v != null) {
				v.lblInfo.setText("Simulacion terminada: no quedan vuelos en transito");
			}
		}
	}

	private int countInTransit() {

		int count = 0;
		Flight[] all = logic.getAllFlights();

		for (int i = 0; i < all.length; i++) {
			if (Flight.IN_TRANSIT.equals(all[i].isStatus())) {
				count++;
			}
		}
		return count;
	}

	private Vertex selectedVertex() {
		String airport = (String) v.cbAirport.getSelectedItem();
		return airport == null ? null : graph.findVertex(airport);
	}

	private void refresh() {

		if (v == null) {
			return;
		}

		v.lblTime.setText("Tiempo: " + logic.getElapsedSeconds() + " s");
		v.inboundModel.setRowCount(0);
		v.pendingModel.setRowCount(0);

		Vertex vertex = selectedVertex();

		if (vertex == null) {
			return;
		}

		Flight[] inbound = vertex.getInbound().toArray();
		for (int i = 0; i < inbound.length; i++) {
			v.inboundModel.addRow(new Object[] { inbound[i].getFlightNumber(), inbound[i].getRoute(),
					inbound[i].getRemainingSeconds() });
		}

		Flight[] pending = vertex.getPending().toArray();
		for (int i = 0; i < pending.length; i++) {
			v.pendingModel.addRow(new Object[] { pending[i].getFlightNumber(), pending[i].getOrigin(),
					pending[i].getFuel() });
		}
	}

	public void index() {

		v = new SimulationIndex();

		String[] names = graph.getNames();
		for (int i = 0; i < names.length; i++) {
			v.cbAirport.addItem(names[i]);
		}

		v.cbAirport.addActionListener(e -> refresh());

		v.btnStart.addActionListener(e -> {

			if (timer.isRunning()) {
				return;
			}

			int started = logic.start();

			if (logic.getSkipped() > 0) {
				JOptionPane.showMessageDialog(null,
						logic.getSkipped() + " vuelo(s) no salieron: aeropuerto inexistente o sin ruta");
			}

			if (countInTransit() == 0) {
				JOptionPane.showMessageDialog(null, "No hay vuelos programados ni en transito");
				return;
			}

			v.lblInfo.setText("Simulacion en curso (" + started + " vuelo(s) nuevos en transito)");
			refresh();
			timer.start();
		});

		v.btnPause.addActionListener(e -> {
			timer.stop();
			v.lblInfo.setText("Simulacion en pausa");
		});

		v.btnReset.addActionListener(e -> {
			timer.stop();
			logic.reset();
			flightLogic.save();
			v.textArea.setText("");
			v.lblInfo.setText("Simulacion reiniciada, todos los vuelos vuelven a Programado");
			refresh();
		});

		// ---- PRIORIZAR: corre el Greedy y muestra la traza en el textArea ----
		v.btnPrioritize.addActionListener(e -> {

			Vertex vertex = selectedVertex();
			if (vertex == null) {
				JOptionPane.showMessageDialog(null, "Seleccione un aeropuerto");
				return;
			}

			Flight[] pending = vertex.getPending().toArray();
			if (pending.length == 0) {
				v.textArea.setText("No hay vuelos pendientes de aterrizaje en " + vertex.getName());
				return;
			}

			StringBuilder trace = new StringBuilder("GREEDY en " + vertex.getName()
					+ " (menor combustible primero, empate: menor numero de vuelo)\n\n");
			Flight[] ordered = flightLogic.sortLandingPriority(pending, trace);

			trace.append("ORDEN FINAL DE PRIORIDAD\n");
			for (int i = 0; i < ordered.length; i++) {
				trace.append(i + 1).append(". Vuelo ").append(ordered[i].getFlightNumber())
						.append(" - ").append(ordered[i].getFuel()).append(" min\n");
			}

			v.textArea.setText(trace.toString());
			v.textArea.setCaretPosition(0);
		});

		// ---- AUTORIZAR: deja aterrizar solo al primero de la prioridad ----
		v.btnAuthorize.addActionListener(e -> {

			Vertex vertex = selectedVertex();
			if (vertex == null) {
				JOptionPane.showMessageDialog(null, "Seleccione un aeropuerto");
				return;
			}

			Flight[] pending = vertex.getPending().toArray();
			if (pending.length == 0) {
				JOptionPane.showMessageDialog(null, "No hay vuelos pendientes en " + vertex.getName());
				return;
			}

			Flight first = flightLogic.sortLandingPriority(pending, new StringBuilder())[0];

			if (logic.authorizeLanding(first)) {
				flightLogic.save();
				v.textArea.setText("Aterrizaje autorizado: vuelo " + first.getFlightNumber()
						+ " (" + first.getFuel() + " min). Estado: " + first.isStatus());
				refresh();
			}
		});

		refresh();
		vp.setContent(v, "Simulacion");
	}
}