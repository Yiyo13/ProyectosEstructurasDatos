package com.proyecto.mvc.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FlightData {

	public void saveFlights(CircularDoubleList flights, String path) {

		try (FileWriter writer = new FileWriter(path)) {

			Flight[] allFlights = flights.getAllFlights();

			writer.write("{\n");
			writer.write("  \"flights\": [\n");

			for (int i = 0; i < allFlights.length; i++) {

				Flight flight = allFlights[i];

				writer.write("    {\n");
				writer.write("      \"flightNumber\": " + flight.getFlightNumber() + ",\n");
				writer.write("      \"route\": \"" + flight.getRoute() + "\",\n");
				writer.write("      \"planeType\": \"" + flight.getPlaneType() + "\",\n");
				writer.write("      \"maximumCapacity\": " + flight.getMaximumCapacity() + ",\n");
				writer.write("      \"status\": \"" + flight.isStatus() + "\",\n");
				writer.write("      \"seats\": [\n");

				NodePassenger aux = flight.getSeats().getFirst();

				while (aux != null) {

					writer.write("        { \"id\": " + aux.getPassenger().getIdP()
							+ ", \"name\": \"" + aux.getPassenger().getNameP()
							+ "\", \"age\": " + aux.getPassenger().getAgeP()
							+ ", \"boarded\": " + aux.getPassenger().isBoarded() + " }");

					aux = aux.getNext();

					if (aux != null) {
						writer.write(",");
					}

					writer.write("\n");
				}

				writer.write("      ],\n");
				writer.write("      \"boardingQueue\": [");

				NodeBoarding auxB = flight.getBoardingQueue().getFirst();

				while (auxB != null) {

					writer.write(String.valueOf(auxB.getPassenger().getIdP()));

					auxB = auxB.getNext();

					if (auxB != null) {
						writer.write(", ");
					}
				}

				writer.write("]\n");
				writer.write("    }");

				if (i < allFlights.length - 1) {
					writer.write(",");
				}

				writer.write("\n");
			}

			writer.write("  ]\n");
			writer.write("}\n");

		} catch (IOException e) {
			System.out.println("Error saving flights: " + e.getMessage());
		}
	}

	public void loadFlights(CircularDoubleList flights, String path) {

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

			String line;
			Flight currentFlight = null;

			int flightNumber = 0;
			String route = "";
			String planeType = "";
			int maximumCapacity = 0;
			String status = "";

			BoardingLogic boardingLogic = new BoardingLogic();

			while ((line = reader.readLine()) != null) {

				line = line.trim();

				if (line.startsWith("\"flightNumber\"")) {
					flightNumber = Integer.parseInt(extractValue(line));

				} else if (line.startsWith("\"route\"")) {
					route = extractValue(line);

				} else if (line.startsWith("\"planeType\"")) {
					planeType = extractValue(line);

				} else if (line.startsWith("\"maximumCapacity\"")) {
					maximumCapacity = Integer.parseInt(extractValue(line));

				} else if (line.startsWith("\"status\"")) {
					status = extractValue(line);

					currentFlight = new Flight(flightNumber, route, planeType, maximumCapacity, status);
					flights.add(currentFlight);

				} else if (line.startsWith("{ \"id\"") && currentFlight != null) {

					int id = Integer.parseInt(extractField(line, "id"));
					String name = extractQuotedField(line, "name");
					int age = Integer.parseInt(extractField(line, "age"));
					boolean boarded = Boolean.parseBoolean(extractField(line, "boarded"));

					Passenger passenger = new Passenger(id, name, age);
					passenger.setBoarded(boarded);

					currentFlight.getSeats().addPassenger(passenger);

				} else if (line.startsWith("\"boardingQueue\"") && currentFlight != null) {

					int openBracket = line.indexOf("[");
					int closeBracket = line.indexOf("]");
					String arrayContent = line.substring(openBracket + 1, closeBracket).trim();

					if (!arrayContent.isEmpty()) {

						String[] ids = arrayContent.split(",");

						for (String idStr : ids) {

							int passengerId = Integer.parseInt(idStr.trim());
							Passenger passenger = findPassengerById(currentFlight, passengerId);

							if (passenger != null) {
								boardingLogic.addInQueue(currentFlight.getBoardingQueue(), currentFlight, passenger);
							}
						}
					}
				}
			}

		} catch (IOException e) {
			System.out.println("No flights file found, using initial data.");
		}
	}

	public void saveTrips(TripStack tripStack, String path) {

		try (FileWriter writer = new FileWriter(path)) {

			writer.write("{\n");
			writer.write("  \"trips\": [\n");

			NodeTrip aux = tripStack.getTop();

			while (aux != null) {

				writer.write("    { \"flightNumber\": " + aux.getTrip().getFlight().getFlightNumber()
						+ ", \"passengerId\": " + aux.getTrip().getPassenger().getIdP() + " }");

				aux = aux.getNext();

				if (aux != null) {
					writer.write(",");
				}

				writer.write("\n");
			}

			writer.write("  ]\n");
			writer.write("}\n");

		} catch (IOException e) {
			System.out.println("Error saving trips: " + e.getMessage());
		}
	}

	public void loadTrips(TripStack tripStack, TripLogic tripLogic, CircularDoubleList flights, String path) {

		try {

			int count = countTripLines(path);

			if (count == 0) {
				return;
			}

			int[] flightNumbers = new int[count];
			int[] passengerIds = new int[count];

			try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

				String line;
				int index = 0;

				while ((line = reader.readLine()) != null) {

					line = line.trim();

					if (line.startsWith("{ \"flightNumber\"")) {

						flightNumbers[index] = Integer.parseInt(extractField(line, "flightNumber"));
						passengerIds[index] = Integer.parseInt(extractField(line, "passengerId"));
						index++;
					}
				}
			}

			for (int i = count - 1; i >= 0; i--) {

				Flight flight = findFlightByNumber(flights, flightNumbers[i]);

				if (flight == null) {
					continue;
				}

				Passenger passenger = findPassengerById(flight, passengerIds[i]);

				if (passenger == null) {
					continue;
				}

				tripLogic.push(tripStack, new Trip(flight, passenger));
			}

		} catch (IOException e) {
			System.out.println("No trips file found, using empty trips.");
		}
	}

	private int countTripLines(String path) throws IOException {

		int count = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

			String line;

			while ((line = reader.readLine()) != null) {

				line = line.trim();

				if (line.startsWith("{ \"flightNumber\"")) {
					count++;
				}
			}
		}

		return count;
	}

	private Passenger findPassengerById(Flight flight, int id) {

		NodePassenger aux = flight.getSeats().getFirst();

		while (aux != null) {

			if (aux.getPassenger().getIdP() == id) {
				return aux.getPassenger();
			}

			aux = aux.getNext();
		}

		return null;
	}

	private Flight findFlightByNumber(CircularDoubleList flights, int flightNumber) {

		Flight[] allFlights = flights.getAllFlights();

		for (int i = 0; i < allFlights.length; i++) {

			if (allFlights[i].getFlightNumber() == flightNumber) {
				return allFlights[i];
			}
		}

		return null;
	}

	private String extractValue(String line) {

		int colonIndex = line.indexOf(":");
		String value = line.substring(colonIndex + 1).trim();

		if (value.endsWith(",")) {
			value = value.substring(0, value.length() - 1);
		}

		value = value.replace("\"", "");

		return value.trim();
	}

	private String extractField(String line, String fieldName) {

		int fieldIndex = line.indexOf("\"" + fieldName + "\"");
		int colonIndex = line.indexOf(":", fieldIndex);

		int commaIndex = line.indexOf(",", colonIndex);
		int braceIndex = line.indexOf("}", colonIndex);

		int endIndex;

		if (commaIndex == -1) {
			endIndex = braceIndex;
		} else if (braceIndex == -1) {
			endIndex = commaIndex;
		} else {
			endIndex = Math.min(commaIndex, braceIndex);
		}

		return line.substring(colonIndex + 1, endIndex).trim();
	}

	private String extractQuotedField(String line, String fieldName) {

		String rawValue = extractField(line, fieldName);
		return rawValue.replace("\"", "").trim();
	}
}