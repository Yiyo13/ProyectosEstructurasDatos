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
		trace.append("Iniciando asignacion por Backingtrack");
		
		backTrack(0, flights, gates, trace);
		
		if(solutionFound) {
			
			trace.append("\n solucion encontrada!!! \n");
			for (int i = 0; i < flights.length; i++) {
				trace.append("vuelo").append(flights[i].getFlightNumber()).append(" (Tamaño: ").append(flights[i].getSize()).append(")")
				.append("Asignado a puerta ").append(gates[assignmentResult[i]].getId()).append("\n");
			}	
		}else {
			trace.append("\n*** NO existe solucion valida para esta combinación. ***\n");
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
			
			if(currentGate.isCompatible(currentFlight.getSize()) && !isGateTaken(gateIndex)) {
				
				assignmentResult[flightIndex] = gateIndex;
				
				trace.append("Intento: vuelo ").append(currentFlight.getFlightNumber()).append(" Puerta")
				.append(" Puerta").append(currentGate.getId()).append("(Valido)\n");	
			}
			
			if(backTrack(flightIndex + 1, flights, gates, trace)) {
				
				return true;
			}
			
			assignmentResult[flightIndex] = -1;
			
			trace.append("RETROCESO: Vuelo ").append(currentFlight.getFlightNumber())
			 .append(" libera la Puerta ").append(currentGate.getId())
			 .append(" porque no llevó a una solución.\n");
			
		}
		
		return false;
	}
	
	private boolean isGateTaken(int gateIndex) {
		
		for (int i = 0; i < assignmentResult.length; i++) {
			if(assignmentResult[i] == gateIndex) {
				return true;
			}
		}
		
		return false;
	}
	
}
