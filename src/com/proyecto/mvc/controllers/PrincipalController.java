package com.proyecto.mvc.controllers;

import com.proyecto.mvc.models.BoardingLogic;
import com.proyecto.mvc.models.CircularDoubleList;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightData;
import com.proyecto.mvc.models.Passenger;
import com.proyecto.mvc.models.TripLogic;
import com.proyecto.mvc.models.TripStack;
import com.proyecto.mvc.views.ViewPrincipal;

public class PrincipalController {

	private FlightController flightController;
	private ReservationController reservationController;
	private TripController tripController;
	private BoardingController boardingController;
	private ViewPrincipal vp;
	private CircularDoubleList flights;
	private TripStack tripStack;
	private TripLogic tripLogic;
	private BoardingLogic boardingLogic;
	private FlightData flightD;
	private String flightsFile;

	public PrincipalController() {

		vp = new ViewPrincipal();
		flights = new CircularDoubleList();
		tripStack = new TripStack();
		tripLogic = new TripLogic();
		boardingLogic = new BoardingLogic();
		flightD = new FlightData();
		flightsFile = "flights.json";

		flightController = new FlightController(vp, flights, flightD, flightsFile);
		reservationController = new ReservationController(vp, flights, tripStack, tripLogic, flightD, flightsFile);
		tripController = new TripController(vp, tripStack, tripLogic);
		boardingController = new BoardingController(vp, flights, boardingLogic);
	}

	public void init() {

		flightD.loadFlights(flights, flightsFile);

		if (flights.isEmpty()) {
			loadData();
		}

		flightController.index();

		vp.btnReservationsAndSeats.addActionListener(e -> { reservationController.index(); });
		vp.btnMyTrips.addActionListener(e -> { tripController.index(); });
		vp.btnBoardingQueue.addActionListener(e -> { boardingController.index(); });

		vp.init();
	}

	public void loadData() {

		flights.add(new Flight(12345, "venecia", "Boeing 737", 10, "disponible"));
		flights.add(new Flight(24582, "paris", "Airbus A320", 15, "disponible"));
		flights.add(new Flight(35693, "madrid", "Boeing 787", 20, "disponible"));
		flights.add(new Flight(46714, "londres", "Airbus A330", 12, "disponible"));
		flights.add(new Flight(57825, "roma", "Boeing 737", 8, "disponible"));
	}

}