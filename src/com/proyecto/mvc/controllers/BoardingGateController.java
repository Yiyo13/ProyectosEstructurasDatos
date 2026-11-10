package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;


import com.proyecto.mvc.models.FlightCircularList;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.Gate;
import com.proyecto.mvc.models.GateLogic;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.gates.GateIndex;

public class BoardingGateController {

	private ViewPrincipal vp;
	private FlightCircularList flights;
	private GateLogic gateLogic;

	public BoardingGateController(ViewPrincipal vp, FlightCircularList flights) {
		this.vp = vp;
		this.flights = flights;
		this.gateLogic = new GateLogic();
	}

	public void index() {
		
		GateIndex v = new GateIndex();
		
		
		Gate[] gates = new Gate[] {
			new Gate("P1", new String[]{"Pequeno", "Mediano"}),
			new Gate("P2", new String[]{"Mediano", "Grande"}),
			new Gate("P3", new String[]{"Grande"})
		};

		
		Flight[] allFlights = flights.getAllFlights();
		for (int i = 0; i < allFlights.length; i++) {
			
			v.cbFlight.addItem(allFlights[i].getFlightNumber() + " (" + allFlights[i].getSize() + ")");
		}

		v.btnAssign.addActionListener(e -> {
			
			
			if (allFlights.length < 3) {
				JOptionPane.showMessageDialog(null, "Se necesitan al menos 3 vuelos registrados para la prueba.");
				return;
			}

			Flight[] simultaneousFlights = new Flight[3];
			simultaneousFlights[0] = allFlights[0];
			simultaneousFlights[1] = allFlights[1];
			simultaneousFlights[2] = allFlights[2];

			String result = gateLogic.assignGates(simultaneousFlights, gates);
			
			v.textArea.setText(result);
			v.textArea.setCaretPosition(0);
		});

		vp.setContent(v, "Asignación de Puertas");
	}
}