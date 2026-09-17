package com.proyecto.mvc.controllers;

import com.proyecto.mvc.models.CircularDoubleList;
import com.proyecto.mvc.models.DoubleListPassenger;
import com.proyecto.mvc.models.Passenger;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.reservations.ReservationForm;

public class ReservationController {

	private ViewPrincipal vp;
	private DoubleListPassenger passengers;
	private CircularDoubleList flights;
	
	public ReservationController(ViewPrincipal vp, DoubleListPassenger passengers, CircularDoubleList flights) {
		
		this.vp = vp;
		this.passengers = passengers;
		this.flights = flights;
	}
	
	public void create() {
		
		ReservationForm v = new ReservationForm();
		
		v.btnSave.addActionListener(e->{
			
			int id = Integer.valueOf(v.tIdentification.getText());
			String name = v.tName.getText();
			int age = Integer.valueOf(v.tAge.getText());
			
			Passenger passenger = new Passenger(id, name, age);
			
			passengers.addPassenger(passenger);
		});
		
		vp.setContent(v, "Agregar Pasajero");
	}
	
	public void index() {
		
		
	}
	
}
