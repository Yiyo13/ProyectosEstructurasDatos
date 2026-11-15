package com.proyecto.mvc.models;

public class GateLogic {

	private int[] assignmentResult;
	private boolean solutionFound;
	
	public GateLogic() {
	
	}
	
	public String assignGates(Flight[] flights, Gate[] gates) {
		
		assignmentResult = new int[flights.length];
		for (int i = 0; i < assignmentResult.length; i++) {
			assignmentResult[i] = -1;
		}
		
		solutionFound = false;
		StringBuilder trace = new StringBuilder();
		trace.append("Iniciando asignacion por Backtracking\n");
		
		backTrack(0, flights, gates, trace);
		
		if(solutionFound) {

			trace.append("\n solucion encontrada!!! \n");
			for (int i = 0; i < flights.length; i++) {

				if (assignmentResult[i] != -1) {
					trace.append("Vuelo ").append(flights[i].getFlightNumber())
					.append(" (Tamano: ").append(flights[i].getSize()).append(")")
					.append(" -> Asignado a Puerta ").append(gates[assignmentResult[i]].getId()).append("\n");
				} else {
					trace.append("Vuelo ").append(flights[i].getFlightNumber())
					.append(" -> No se pudo asignar\n");
				}
			}	
			
		}else {
			trace.append("\n*** NO existe solucion valida para esta combinacion. ***\n");
		}
		
		return trace.toString();
	}

	private boolean backTrack(int flightIndex, Flight[] flights, Gate[] gates, StringBuilder trace) {
		
		
		if(flightIndex == flights.length) {
			
			solutionFound = true;
			
			return true;
		}
		
		Flight currentFlight = flights[flightIndex];
		
		for (int gateIndex = 0; gateIndex < gates.length; gateIndex++) {
			Gate currentGate = gates[gateIndex];

			// rechazo 1: la puerta no atiende el tamano de este avion
			if(!currentGate.isCompatible(currentFlight.getSize())) {
				trace.append("   Vuelo ").append(currentFlight.getFlightNumber())
				.append(" -> Puerta ").append(currentGate.getId())
				.append(": tamano incompatible (").append(currentFlight.getSize()).append(")\n");
				continue;
			}

			// rechazo 2: la puerta ya la tiene otro vuelo de este mismo conjunto
			int owner = gateTakenBy(gateIndex);
			if(owner != -1) {
				trace.append("   Vuelo ").append(currentFlight.getFlightNumber())
				.append(" -> Puerta ").append(currentGate.getId())
				.append(": ocupada por vuelo ").append(flights[owner].getFlightNumber()).append("\n");
				continue;
			}

			// la puerta sirve: se asigna y se intenta con el siguiente vuelo
			assignmentResult[flightIndex] = gateIndex;

			trace.append("Intento: vuelo ").append(currentFlight.getFlightNumber())
			.append(" -> Puerta ").append(currentGate.getId()).append(" (Valido)\n");

			if(backTrack(flightIndex + 1, flights, gates, trace)) {

				return true;
			}

			// no llevo a una solucion: se deshace la asignacion y se prueba la siguiente puerta
			assignmentResult[flightIndex] = -1;

			trace.append("RETROCESO: Vuelo ").append(currentFlight.getFlightNumber())
			 .append(" libera la Puerta ").append(currentGate.getId())
			 .append(" porque no llevo a una solucion.\n");
		}

		return false;
	}

	// devuelve el indice del vuelo que tiene esa puerta, o -1 si esta libre
	private int gateTakenBy(int gateIndex) {

		for (int i = 0; i < assignmentResult.length; i++) {
			if(assignmentResult[i] == gateIndex) {
				return i;
			}
		}

		return -1;
	}
	
}
