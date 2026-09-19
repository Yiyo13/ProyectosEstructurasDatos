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
							+ "\", \"age\": " + aux.getPassenger().getAgeP() + " }");

					aux = aux.getNext();

					if (aux != null) {
						writer.write(",");
					}

					writer.write("\n");
				}

				writer.write("      ]\n");
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

					currentFlight.getSeats().addPassenger(new Passenger(id, name, age));
				}
			}

		} catch (IOException e) {
			System.out.println("No flights file found, using initial data.");
		}
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
		int endIndex = line.indexOf(",", colonIndex);

		if (endIndex == -1) {
			endIndex = line.indexOf("}", colonIndex);
		}

		return line.substring(colonIndex + 1, endIndex).trim();
	}

	private String extractQuotedField(String line, String fieldName) {

		String rawValue = extractField(line, fieldName);
		return rawValue.replace("\"", "").trim();
	}
}