package com.proyecto.mvc.models;

public class GreedyLogic {

	public GreedyLogic() {
		
	}
	
	
	public Flight[] sortLandingPriority(Flight[] flights, StringBuilder trace) {
		
		Flight[] result = new Flight[flights.length];
		
		for (int i = 0; i < flights.length; i++) {
			result[i] = flights[i];
		}
		
		int stepNumber = 1;
		
		for (int i = 0; i < result.length; i++) {
			
			int bestIndex = i;
			
			for (int j = i; j < result.length; j++) {
				
				int candidateFuel = flights[j].getFuel();
				int lowestFuel = flights[bestIndex].getFuel();
				
				if(candidateFuel < lowestFuel) {
					bestIndex = j;
					
				}else if(candidateFuel == lowestFuel) {
					
					if(flights[j].getFlightNumber() < flights[bestIndex].getFlightNumber()) {
						
						bestIndex = j;
					}
					
				}	
			}
			
			
			Flight temp = flights[i];
			flights[i] = flights[bestIndex];
			flights[bestIndex] = temp;
			
			trace.append("Paso ").append(stepNumber++).append(": Se selecciona el vuelo").
			append(flights[i].getFlightNumber()).append(" (Combustible: ").append(flights[i].getFuel()).append(" min\n");
			
		}
		
		
		return result;
	}
	
}
