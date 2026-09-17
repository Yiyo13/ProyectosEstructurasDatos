package com.proyecto.mvc.controllers;

import com.proyecto.mvc.models.CircularDoubleList;
import com.proyecto.mvc.models.DoubleListPassenger;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.Passenger;
import com.proyecto.mvc.views.ViewPrincipal;

public class PrincipalController {

	private FlightController flightController;
	private ReservationController reservationController;
	private ViewPrincipal vp;
	private CircularDoubleList flights;
	private DoubleListPassenger passengers;
	
	public PrincipalController() {
		
		flights = new CircularDoubleList(); 
		passengers = new DoubleListPassenger();
		flightController = new FlightController(vp, flights);
		reservationController = new ReservationController(vp,passengers,flights);
		
	}

	
	public void init() {
		
		
		flightController.index();
		vp.btnReservationsAndSeats.addActionListener(e->{reservationController.index();});
		
		
		vp.init();
		loadData();
	}
	
	public void loadData() {
		
		flights.add(new Flight("13451","venecia","Boeing 737",10,"disponible"));
		flights.add(new Flight("24582", "paris", "Airbus A320", 15, "disponible"));
		flights.add(new Flight("35693", "madrid", "Boeing 787", 20, "disponible"));
		flights.add(new Flight("46714", "londres", "Airbus A330", 12, "disponible"));
		flights.add(new Flight("57825", "roma", "Boeing 737", 8, "disponible"));

		passengers.addPassenger(new Passenger(123456, "Ana Pérez",   25));

		passengers.addPassenger(new Passenger(789101, "Carla Rojas", 22));

		passengers.addPassenger(new Passenger(234567, "Luis Mora",   30));

		passengers.addPassenger(new Passenger(345678, "Mario Vargas", 45));

		passengers.addPassenger(new Passenger(456789, "Sofía Jiménez", 28));

		passengers.addPassenger(new Passenger(567890, "Diego Salas",  33));
	}

	
}
