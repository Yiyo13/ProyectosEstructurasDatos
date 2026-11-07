package com.proyecto.mvc.models;

public class Flight {

	public static final String SCHEDULED = "Programado";
	public static final String IN_TRANSIT = "En transito";
	public static final String PENDING = "Pendiente de aterrizaje";
	public static final String LANDED = "Aterrizado";

	public static final String[] PLANES = {"ATR 72", "Embraer E190", "Boeing 737", "Airbus A320", "Boeing 787", "Airbus A330"};

	private int flightNumber;
	private String origin;
	private String destination;
	private String planeType;
	private int maximumCapacity;
	private int fuel;
	private String size;
	private String status;
	private DoubleListPassenger seats;//asientos
	private BoardingQueue boardingQueue;

	public Flight(int flightNumber, String origin, String destination, String planeType, int maximumCapacity, int fuel, String status) {

		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.planeType = planeType;
		this.maximumCapacity = maximumCapacity;
		this.fuel = fuel;
		this.size = sizeOf(planeType);
		this.status = status;

		this.seats = new DoubleListPassenger(maximumCapacity);
		this.boardingQueue = new BoardingQueue();
	}

	public static String sizeOf(String planeType) {// tamano segun avion

		if (planeType.equals("ATR 72") || planeType.equals("Embraer E190")) {
			return "Pequeno";
		}
		if (planeType.equals("Boeing 737") || planeType.equals("Airbus A320")) {
			return "Mediano";
		}
		return "Grande";
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getRoute() {
		return origin + " - " + destination;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
		this.size = sizeOf(planeType);
	}

	public int getMaximumCapacity() {
		return maximumCapacity;
	}

	public void setMaximumCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
		this.seats.setMaxCapFlight(maximumCapacity);
	}

	public int getFuel() {
		return fuel;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	public String getSize() {
		return size;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DoubleListPassenger getSeats() {
		return seats;
	}

	public void setSeats(DoubleListPassenger seats) {
		this.seats = seats;
	}

	public double getOccupancyPercentage() {

		return (double) this.seats.getCurrentCapacity()/this.maximumCapacity;
	}

	public int getAvailableSeats() {

		return this.maximumCapacity - this.seats.getCurrentCapacity();
	}

	public BoardingQueue getBoardingQueue() {
		return boardingQueue;
	}

	@Override
	public String toString() {
		return "Vuelo " + flightNumber + "\n" +
				"  Origen: "      + origin      + "\n" +
				"  Destino: "     + destination + "\n" +
				"  Avion: "       + planeType   + " (" + size + ")\n" +
				"  Combustible: " + fuel + " min\n" +
				"  Capacidad: "   + maximumCapacity + "\n" +
				"  Pasajeros registrados: " + this.seats.getCurrentCapacity() + "\n" +
				"  Espacios disponibles: "  + getAvailableSeats() + "\n" +
				"  Estado: "      + status;
	}

	public String printReservations() {

		return "Vuelo " + flightNumber + "\n" +
				"  Ruta: "      + getRoute()  + "\n" +
				"  Capacidad: " + maximumCapacity + "\n" +
				"  Pasajeros registrados: " + this.seats.getCurrentCapacity() + "\n" +
				"  Espacios disponibles: "  + getAvailableSeats() + "\n\n" +
				getSeats().toString();

	}

}
