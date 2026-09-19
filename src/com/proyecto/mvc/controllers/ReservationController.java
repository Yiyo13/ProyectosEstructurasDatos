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
import com.proyecto.mvc.views.reservations.ReservationIndex;

public class ReservationController {

	private ViewPrincipal vp;
	private CircularDoubleList flights;
	private TripStack tripStack;
	private TripLogic tripLogic;
	private FlightData flightD;
	private String flightsFile;
	private String tripsFile;

	public ReservationController(ViewPrincipal vp, CircularDoubleList flights,
			TripStack tripStack, TripLogic tripLogic, FlightData flightD, String flightsFile, String tripsFile) {
		this.vp = vp;
		this.flights = flights;
		this.tripStack = tripStack;
		this.tripLogic = tripLogic;
		this.flightD = flightD;
		this.flightsFile = flightsFile;
		this.tripsFile = tripsFile;
	}
	
	public void create() {
		
		ReservationForm v = new ReservationForm();
		
		v.btnSave.addActionListener(e->{
			
			try {
				
				Flight flight = flights.getCurrent();
				
				if(flight == null) {
					JOptionPane.showMessageDialog(null, "No hay un vuelo seleccionado");
					return;
				}
				
				int id = Integer.valueOf(v.tIdentification.getText());
				String name = v.tName.getText();
				int age = Integer.valueOf(v.tAge.getText());
				
				if (name == null || name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio");
					return;
				}
				
				Passenger passenger = new Passenger(id, name, age);
				
				boolean added = flight.getSeats().addPassenger(passenger);
				
				if(added) {
					tripLogic.push(tripStack, new Trip(flight, passenger));
					flightD.saveFlights(flights, flightsFile);
					flightD.saveTrips(tripStack, tripsFile);
					index();
				}else {
					JOptionPane.showMessageDialog(null, "El vuelo no tiene asientos disponibles");
				}
				
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Identificacion y edad deben ser numeros");
			}
		});

		v.btnCancel.addActionListener(e -> {
			index();
		});

		vp.setContent(v, "Agregar Pasajero");
	}
	
	public void index() {

		ReservationIndex v = new ReservationIndex();

		Flight[] allFlights = flights.getAllFlights();

		for (int i = 0; i < allFlights.length; i++) {
			v.comboBox.addItem(allFlights[i].getFlightNumber());
		}

		Flight current = flights.getCurrent();
		if (current != null) {
			v.textArea.setText(current.printReservations());
			v.comboBox.setSelectedItem(current.getFlightNumber());
		}

		v.comboBox.addActionListener(e -> {

			Object selected = v.comboBox.getSelectedItem();

			if (selected != null) {

				int flightNumber = (int) selected;

				if (flights.selectFlight(flightNumber)) {
					v.textArea.setText(flights.getCurrent().printReservations());
				}
			}
		});

		v.btnStart.addActionListener(e -> {

			v.textArea.setText(flights.getPrevious().printReservations());
			v.comboBox.setSelectedItem(flights.getCurrent().getFlightNumber());
		});

		v.btnEnd.addActionListener(e -> {

			v.textArea.setText(flights.getNext().printReservations());
			v.comboBox.setSelectedItem(flights.getCurrent().getFlightNumber());
		});
		
		v.btnReservation.addActionListener(e -> {
			create();
		});
		
		v.btnSortAsc.addActionListener(e -> {

			Flight flight = flights.getCurrent();
			if (flight == null) {
				JOptionPane.showMessageDialog(null, "No hay un vuelo seleccionado");
				return;
			}

			flight.getSeats().sortByAge(true);
			v.textArea.setText(flight.printReservations());
		});

		v.btnSortDesc.addActionListener(e -> {

			Flight flight = flights.getCurrent();
			if (flight == null) {
				JOptionPane.showMessageDialog(null, "No hay un vuelo seleccionado");
				return;
			}

			flight.getSeats().sortByAge(false);
			v.textArea.setText(flight.printReservations());
		});
		
		vp.setContent(v, "Reservar y Asientos");
	}
	
}