package com.proyecto.mvc.controllers;

import com.proyecto.mvc.models.AVLTree;
import com.proyecto.mvc.models.AirportData;
import com.proyecto.mvc.models.BoardingLogic;
import com.proyecto.mvc.models.FlightCircularList;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightData;
import com.proyecto.mvc.models.FlightLogic;
import com.proyecto.mvc.models.Graph;
import com.proyecto.mvc.models.TripLogic;
import com.proyecto.mvc.models.Stack;
import com.proyecto.mvc.models.Trip;
import com.proyecto.mvc.views.ViewPrincipal;

public class PrincipalController {

	private FlightController flightController;
	private ReservationController reservationController;
	private TripController tripController;
	private BoardingController boardingController;
	private AVLController avlController;
	private AirportController airportController;
	private RouteController routeController;
	private ViewPrincipal vp;
	private FlightCircularList flights;
	private AVLTree avl;
	private Graph graph;
	private Stack<Trip> tripStack;
	private TripLogic tripLogic;
	private BoardingLogic boardingLogic;
	private FlightLogic flightLogic;
	private FlightData flightD;
	private AirportData airportD;
	private String flightsFile;
	private String tripsFile;
	private String airportsFile;
	private LandingController landingController;
	
	
	public PrincipalController() {

		vp = new ViewPrincipal();
		flights = new FlightCircularList();
		avl = new AVLTree();
		graph = new Graph();
		tripStack = new Stack<Trip>();
		tripLogic = new TripLogic();
		boardingLogic = new BoardingLogic();
		flightD = new FlightData();
		airportD = new AirportData();
		flightsFile = "flights.json";
		tripsFile = "trips.json";
		airportsFile = "airports.json";
		flightLogic = new FlightLogic(flights, avl, flightD, flightsFile);

		flightController = new FlightController(vp, flights, flightLogic, graph);
		reservationController = new ReservationController(vp, flights, tripStack, tripLogic, flightD, flightsFile, tripsFile);
		tripController = new TripController(vp, tripStack, tripLogic);
		boardingController = new BoardingController(vp, flights, boardingLogic, flightD, flightsFile);
		avlController = new AVLController(vp, avl, flightLogic);
		airportController = new AirportController(vp, graph, airportD, airportsFile);
		routeController = new RouteController(vp, graph);
		landingController = new LandingController(vp, flightLogic, graph);
		
	}

	public void init() {

		airportD.loadAirports(graph, airportsFile);

		if (graph.isEmpty()) {
			loadAirports();
			airportD.saveAirports(graph, airportsFile);
		}

		flightD.loadFlights(flights, flightsFile);

		if (flights.isEmpty()) {
			loadData();
		}

		flightLogic.buildTree();
		flightD.loadTrips(tripStack, tripLogic, flights, tripsFile);

		flightController.index();

		vp.btnReservationsAndSeats.addActionListener(e -> { reservationController.index(); });
		vp.btnMyTrips.addActionListener(e -> { tripController.index(); });
		vp.btnBoardingQueue.addActionListener(e -> { boardingController.index(); });
		vp.btnFlights.addActionListener(e -> { flightController.index(); });
		vp.btnAVL.addActionListener(e -> { avlController.index(); });
		vp.btnAirports.addActionListener(e -> { airportController.index(); });
		vp.btnRoutes.addActionListener(e -> { routeController.index(); });
		vp.btnLanding.addActionListener(e->{landingController.index();});
		vp.btnBoardingGates.addActionListener(e->{boardingController.index();});
		
		vp.init();
	}

	public void loadData() {

		flights.add(new Flight(12345, "San Jose", "Venecia", "Boeing 737", 10, 45, Flight.SCHEDULED));
		flights.add(new Flight(24582, "San Jose", "Paris", "Airbus A320", 15, 30, Flight.SCHEDULED));
		flights.add(new Flight(35693, "San Jose", "Madrid", "Boeing 787", 20, 60, Flight.SCHEDULED));
		flights.add(new Flight(46714, "San Jose", "Londres", "Airbus A330", 12, 30, Flight.SCHEDULED));
		flights.add(new Flight(57825, "San Jose", "Roma", "Boeing 737", 8, 20, Flight.SCHEDULED));
	}

	public void loadAirports() {

		String[] names = {"San Jose", "Bogota", "Miami", "Nueva York", "Madrid", "Londres", "Paris", "Roma", "Venecia"};

		for (int i = 0; i < names.length; i++) {
			graph.addVertex(names[i]);
		}

		graph.addEdge("San Jose", "Bogota", 1280);
		graph.addEdge("San Jose", "Miami", 1790);
		graph.addEdge("San Jose", "Madrid", 8470);
		graph.addEdge("Bogota", "Miami", 2430);
		graph.addEdge("Bogota", "Madrid", 8030);
		graph.addEdge("Miami", "Nueva York", 1760);
		graph.addEdge("Miami", "Madrid", 7110);
		graph.addEdge("Nueva York", "Madrid", 5770);
		graph.addEdge("Nueva York", "Londres", 5570);
		graph.addEdge("Nueva York", "Paris", 5840);
		graph.addEdge("Madrid", "Londres", 1260);
		graph.addEdge("Madrid", "Paris", 1050);
		graph.addEdge("Madrid", "Roma", 1360);
		graph.addEdge("Londres", "Paris", 340);
		graph.addEdge("Paris", "Roma", 1100);
		graph.addEdge("Paris", "Venecia", 850);
		graph.addEdge("Roma", "Venecia", 400);
	}

}
