package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.CircularDoubleList;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightData;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.flights.FlightForm;
import com.proyecto.mvc.views.flights.FlightIndex;

public class FlightController {

	private ViewPrincipal vp;
	private CircularDoubleList flights;
	private FlightData flightD;
	private String flightsFile;

	public FlightController(ViewPrincipal vp, CircularDoubleList flights, FlightData flightD , String flightsFile) {
		this.vp = vp;
		this.flights = flights;
		this.flightD = flightD;
		this.flightsFile = flightsFile;
	}

	public void create() {
		
		FlightForm form = new FlightForm();
		
		form.btnSave.addActionListener(e->{
			
			try {
				
				int flightNumber = Integer.valueOf(form.tFlightNumber.getText());
				
				if(flights.checkDuplicateFlightNumber(flightNumber)) {
					JOptionPane.showMessageDialog(null, "El numero de vuelo ya existe");
					return;
				}
				
				String route = form.tRoute.getText();
				String plane = (String)form.cbPlane.getSelectedItem();
				int maximumCapacity = Integer.valueOf(form.tMaximumCapcity.getText());
				String status = (String) form.cbStatus.getSelectedItem();
				
				Flight flight = new Flight(flightNumber,route,plane,maximumCapacity,status);
				flights.add(flight);
				flightD.saveFlights(flights, flightsFile);
				
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Numero de vuelo y capacidad deben ser numeros");
			}
			
		});
		form.btnBack.addActionListener(e->{
			index();
		});
		vp.setContent(form, "Registrar Vuelo");
	}
	
	public void index() {
		
		FlightIndex v = new FlightIndex();
		
		v.textArea.setText(flights.getCurrent().toString());
		
		v.btnPrevious.addActionListener(e->{
			
			v.textArea.setText(flights.getPrevious().toString());
		});
		
		v.btnNext.addActionListener(e->{
			
			v.textArea.setText(flights.getNext().toString());
		});
		
		
		v.btnRegister.addActionListener(e->{
			create();
		});
		
		
		v.btnPrioritizeFlights.addActionListener(e->{
			
			flights.prioritizeFlights();
			//flights.print();
			
			v.textArea.setText(flights.getHead().getFlight().toString());
			
		});
		
		vp.setContent(v, "Lista de Vuelos");
	}
	
	
	
}