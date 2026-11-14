package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.CircularDoubleList;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightCircularList;
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

		// Definimos las puertas disponibles (como en el ejemplo del PDF)
		Gate[] gates = new Gate[] {
			new Gate("P1", new String[]{"Pequeno", "Mediano"}),
			new Gate("P2", new String[]{"Mediano", "Grande"}),
			new Gate("P3", new String[]{"Grande"})
		};

		v.btnAssign.addActionListener(e -> {
			
			// 1. Leemos los números de vuelo que el usuario escribió en el JTextField
			String input = v.tFlights.getText().trim();
			
			if (input.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe ingresar al menos un número de vuelo.");
				return;
			}

			// 2. Separamos los números por coma
			String[] parts = input.split(",");
			
			// Creamos el arreglo de vuelos para el algoritmo
			Flight[] selectedFlights = new Flight[parts.length];

			// 3. Buscamos cada vuelo en la lista general del sistema
			for (int i = 0; i < parts.length; i++) {
				try {
					int flightNumber = Integer.parseInt(parts[i].trim());
					Flight found = findFlightByNumber(flightNumber);

					if (found == null) {
						JOptionPane.showMessageDialog(null, "El vuelo " + flightNumber + " no existe en el sistema.");
						return;
					}

					selectedFlights[i] = found;

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Asegúrese de ingresar solo números válidos separados por coma.");
					return;
				}
			}

			// 4. Ejecutamos el Backtracking
			String result = gateLogic.assignGates(selectedFlights, gates);
			
			// 5. Mostramos el resultado en el área de texto
			v.textArea.setText(result);
			v.textArea.setCaretPosition(0); // Mueve el scroll al inicio
		});

		vp.setContent(v, "Asignación de Puertas");
	}

	// Método auxiliar para buscar un vuelo por su número en la lista general
	private Flight findFlightByNumber(int flightNumber) {
		Flight[] allFlights = flights.getAllFlights();
		
		for (int i = 0; i < allFlights.length; i++) {
			if (allFlights[i].getFlightNumber() == flightNumber) {
				return allFlights[i];
			}
		}
		return null;
	}
}