package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.AirportData;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.Gate;
import com.proyecto.mvc.models.GateLogic;
import com.proyecto.mvc.models.Graph;
import com.proyecto.mvc.models.Vertex;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.gates.GateIndex;

public class BoardingGateController {

	private ViewPrincipal vp;
	private Graph graph;
	private AirportData airportD;
	private String airportsFile;
	private GateLogic gateLogic;
	private Flight[] landed;// vuelos que se muestran en la lista, en el mismo orden

	public BoardingGateController(ViewPrincipal vp, Graph graph, AirportData airportD, String airportsFile) {
		this.vp = vp;
		this.graph = graph;
		this.airportD = airportD;
		this.airportsFile = airportsFile;
		this.gateLogic = new GateLogic();
		this.landed = new Flight[0];
	}

	private Vertex selectedAirport(GateIndex v) {
		String airport = (String) v.cbAirport.getSelectedItem();
		return airport == null ? null : graph.findVertex(airport);
	}

	// muestra las puertas y los vuelos aterrizados del aeropuerto seleccionado
	private void refresh(GateIndex v) {

		v.gatesArea.setText("");
		v.flightsModel.clear();
		landed = new Flight[0];

		Vertex vertex = selectedAirport(v);

		if (vertex == null) {
			return;
		}

		Gate[] gates = vertex.getGates().toArray(new Gate[vertex.getGates().size()]);

		if (gates.length == 0) {
			v.gatesArea.setText("Este aeropuerto no tiene puertas registradas");
		}
		for (int i = 0; i < gates.length; i++) {
			v.gatesArea.append(gates[i].getId() + "  (" + gates[i].getSizesText() + ")\n");
		}

		landed = vertex.getLanded().toArray();

		for (int i = 0; i < landed.length; i++) {
			v.flightsModel.addElement(landed[i].getFlightNumber() + " - " + landed[i].getPlaneType()
					+ " (" + landed[i].getSize() + ")");
		}
	}

	public void index() {
		GateIndex v = new GateIndex();

		String[] names = graph.getNames();
		for (int i = 0; i < names.length; i++) {
			v.cbAirport.addItem(names[i]);
		}

		v.cbAirport.addActionListener(e -> {
			v.textArea.setText("");
			refresh(v);
		});

		// ---- REGISTRAR PUERTA en el aeropuerto seleccionado ----
		v.btnAddGate.addActionListener(e -> {

			Vertex vertex = selectedAirport(v);
			if (vertex == null) {
				JOptionPane.showMessageDialog(null, "Debe registrar aeropuertos primero");
				return;
			}

			String id = v.tGateId.getText().trim();
			if (id.isEmpty()) {
				JOptionPane.showMessageDialog(null, "El id de la puerta no puede estar vacio");
				return;
			}
			if (id.contains("\"") || id.contains(",") || id.contains("/")) {// romperian el JSON
				JOptionPane.showMessageDialog(null, "El id no puede tener comillas, comas ni /");
				return;
			}
			if (vertex.findGate(id) != null) {
				JOptionPane.showMessageDialog(null, "La puerta " + id + " ya existe en " + vertex.getName());
				return;
			}

			int count = 0;
			if (v.chkSmall.isSelected()) count++;
			if (v.chkMedium.isSelected()) count++;
			if (v.chkLarge.isSelected()) count++;

			if (count == 0) {
				JOptionPane.showMessageDialog(null, "Seleccione al menos un tamano de avion");
				return;
			}

			String[] sizes = new String[count];
			int index = 0;
			if (v.chkSmall.isSelected()) sizes[index++] = "Pequeno";
			if (v.chkMedium.isSelected()) sizes[index++] = "Mediano";
			if (v.chkLarge.isSelected()) sizes[index++] = "Grande";

			vertex.getGates().add(new Gate(id, sizes));
			airportD.saveAirports(graph, airportsFile);

			v.tGateId.setText("");
			v.chkSmall.setSelected(false);
			v.chkMedium.setSelected(false);
			v.chkLarge.setSelected(false);
			refresh(v);
		});

		// ---- EJECUTAR BACKTRACKING con los vuelos seleccionados (simultaneos) ----
		v.btnAssign.addActionListener(e -> {

			Vertex vertex = selectedAirport(v);
			if (vertex == null) {
				JOptionPane.showMessageDialog(null, "Seleccione un aeropuerto");
				return;
			}

			if (vertex.getGates().isEmpty()) {
				JOptionPane.showMessageDialog(null, vertex.getName() + " no tiene puertas registradas");
				return;
			}

			// solo se pueden elegir vuelos aterrizados de este aeropuerto, y cada uno una sola vez
			int[] selected = v.listFlights.getSelectedIndices();

			if (selected.length == 0) {
				JOptionPane.showMessageDialog(null, "Seleccione al menos un vuelo aterrizado");
				return;
			}

			Flight[] selectedFlights = new Flight[selected.length];
			for (int i = 0; i < selected.length; i++) {
				selectedFlights[i] = landed[selected[i]];
			}

			Gate[] gates = vertex.getGates().toArray(new Gate[vertex.getGates().size()]);

			String result = gateLogic.assignGates(selectedFlights, gates);

			v.textArea.setText("Aeropuerto: " + vertex.getName() + "\n" + result);
			v.textArea.setCaretPosition(0); // Mueve el scroll al inicio
		});

		refresh(v);
		vp.setContent(v, "Asignacion de Puertas");
	}
}
