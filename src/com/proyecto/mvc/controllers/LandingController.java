package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightLogic;
import com.proyecto.mvc.models.Graph;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.landings.LandingIndex;

public class LandingController {

	private ViewPrincipal vp;
	private FlightLogic flightLogic;
	private Graph graph;
	
	public LandingController(ViewPrincipal vp, FlightLogic flightLogic, Graph graph) {
		
		this.vp = vp;
		this.flightLogic = flightLogic;
		this.graph = graph;
	}

	public void index() {
		
		LandingIndex v = new LandingIndex();
		
		String[] names = graph.getNames();
		
		for(int i = 0; i < names.length; i++) {
			
			v.cbAirport.addItem(names[i]);
		}
		
		v.btnExecute.addActionListener(e->{
			
			String airport = (String) v.cbAirport.getSelectedItem();
			
			if(airport == null) {
				JOptionPane.showMessageDialog(null, "No hay repuestos registrados");
				return;
			}
			
			Flight[] pending = flightLogic.getPendingFlightsByAirport(airport);
			
			if(pending.length == 0) {
				
				v.textArea.setText("No hay vuelos de aterrizaje en "+airport);
				return;
			}
			
			String text = "Vuelos pendientes en "+airport +"\n";
			for (int i = 0; i < pending.length; i++) {
				text += "Vuelo "+pending[i].getFlightNumber() + "| Combustible "+pending[i].getFuel() + "min\n";
			}
			
			text+="\n*** Ejecutando Greedy ***\n";
			StringBuilder trace = new StringBuilder();
			
			Flight[] ordered = flightLogic.sortLandingPriority(pending, trace);
			text += trace.toString();
			
			for(int i = 0; i< ordered.length; i++) {
				
				text += (i+1) + ". Vuelo" + ordered[i].getFlightNumber()+"(Combustible: "+ordered[i].getFuel() + "min)\n";
			}
			
			v.textArea.setText(text);
			v.textArea.setCaretPosition(0);//con esto el scroll vuelve arriba
			
		});
		
		
		vp.setContent(v, "Prioridad de aterrizaje");
	}
	
	
}
