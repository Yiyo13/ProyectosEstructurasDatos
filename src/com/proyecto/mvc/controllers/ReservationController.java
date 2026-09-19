package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.CircularDoubleList;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightData;
import com.proyecto.mvc.models.Passenger;
import com.proyecto.mvc.models.Trip;
import com.proyecto.mvc.models.TripLogic;
import com.proyecto.mvc.models.TripStack;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.reservations.ReservationForm;

public class ReservationController {

	private ViewPrincipal vp;
	private CircularDoubleList flights;
	private TripStack tripStack;
	private TripLogic tripLogic;
	private FlightData flightD;
	private String flightsFile;

	public ReservationController(ViewPrincipal vp, CircularDoubleList flights,
			TripStack tripStack, TripLogic tripLogic, FlightData flightD, String flightsFile) {
		this.vp = vp;
		this.flights = flights;
		this.tripStack = tripStack;
		this.tripLogic = tripLogic;
		this.flightD = flightD;
		this.flightsFile = flightsFile;
	}
	
	public void create() {
		
		ReservationForm v = new ReservationForm();
		
		v.btnSave.addActionListener(e->{
			
			Flight flight = flights.getCurrent();
			
			if(flight == null) {
				JOptionPane.showMessageDialog(null, "No hay un vuelo seleccionado");
				return;
			}
			
			int id = Integer.valueOf(v.tIdentification.getText());
			String name = v.tName.getText();
			int age = Integer.valueOf(v.tAge.getText());
			
			Passenger passenger = new Passenger(id, name, age);
			
			boolean added = flight.getSeats().addPassenger(passenger);
			
			if(added) {
				tripLogic.push(tripStack, new Trip(flight, passenger));
				flightD.saveFlights(flights, flightsFile);
			}else {
				JOptionPane.showMessageDialog(null, "El vuelo no tiene asientos disponibles");
			}
		});
		
		vp.setContent(v, "Agregar Pasajero");
	}
	
	public void index() {
		
		
	}
	
}