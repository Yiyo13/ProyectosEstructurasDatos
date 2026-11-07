package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.AirportData;
import com.proyecto.mvc.models.Graph;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.airports.AirportIndex;

public class AirportController {

	private ViewPrincipal vp;
	private Graph graph;
	private AirportData airportD;
	private String airportsFile;

	public AirportController(ViewPrincipal vp, Graph graph, AirportData airportD, String airportsFile) {
		this.vp = vp;
		this.graph = graph;
		this.airportD = airportD;
		this.airportsFile = airportsFile;
	}

	private void loadAirports(AirportIndex v) {

		String[] names = graph.getNames();

		v.listModel.clear();
		v.cbOrigin.removeAllItems();
		v.cbDestination.removeAllItems();

		for (int i = 0; i < names.length; i++) {
			v.listModel.addElement(names[i]);
			v.cbOrigin.addItem(names[i]);
			v.cbDestination.addItem(names[i]);
		}
	}

	public void index() {

		AirportIndex v = new AirportIndex();
		loadAirports(v);

		v.listAirports.addListSelectionListener(e -> {

			String selected = v.listAirports.getSelectedValue();

			if (selected != null) {
				v.textArea.setText(graph.showConnections(selected));
			}
		});

		v.btnAddAirport.addActionListener(e -> {

			String name = v.tName.getText().trim();

			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
				return;
			}

			if (!graph.addVertex(name)) {
				JOptionPane.showMessageDialog(null, "El aeropuerto ya existe");
				return;
			}

			airportD.saveAirports(graph, airportsFile);
			v.tName.setText("");
			loadAirports(v);
		});

		v.btnAddConnection.addActionListener(e -> {

			String origin = (String) v.cbOrigin.getSelectedItem();
			String destination = (String) v.cbDestination.getSelectedItem();
			int distance;

			try {
				distance = Integer.parseInt(v.tDistance.getText().trim());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "La distancia debe ser un numero");
				return;
			}

			String error = graph.addEdge(origin, destination, distance);

			if (error != null) {
				JOptionPane.showMessageDialog(null, error);
				return;
			}

			airportD.saveAirports(graph, airportsFile);
			v.tDistance.setText("");
			v.listAirports.setSelectedValue(origin, true);
			v.textArea.setText(graph.showConnections(origin));
		});

		vp.setContent(v, "Aeropuertos");
	}
}
