package com.proyecto.mvc.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AirportData {

	public void saveAirports(Graph graph, String path) {

		try (FileWriter writer = new FileWriter(path)) {

			String[] names = graph.getNames();

			writer.write("{\n");
			writer.write("  \"airports\": [\n");

			for (int i = 0; i < names.length; i++) {
				writer.write("    \"" + names[i] + "\"");
				if (i < names.length - 1) {
					writer.write(",");
				}
				writer.write("\n");
			}

			writer.write("  ],\n");
			writer.write("  \"connections\": [\n");

			String connections = "";
			VertexNode aux = graph.getStart();

			while (aux != null) {

				EdgeNode edge = aux.getVertex().getAdjacencyList();

				while (edge != null) {

					String origin = aux.getVertex().getName();
					String destination = edge.getDestination().getName();

					if (origin.compareTo(destination) < 0) {// una vez por par
						if (!connections.isEmpty()) {
							connections += ",\n";
						}
						connections += "    { \"origin\": \"" + origin + "\", \"destination\": \"" + destination
								+ "\", \"distance\": " + edge.getWeight() + " }";
					}
					edge = edge.getNext();
				}
				aux = aux.getNext();
			}

			if (!connections.isEmpty()) {
				writer.write(connections + "\n");
			}

			writer.write("  ]\n");
			writer.write("}\n");

		} catch (IOException e) {
			System.out.println("Error saving airports: " + e.getMessage());
		}
	}

	public void loadAirports(Graph graph, String path) {

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

			String line;
			boolean readingAirports = false;

			while ((line = reader.readLine()) != null) {

				line = line.trim();

				if (line.startsWith("\"airports\"")) {
					readingAirports = true;

				} else if (line.startsWith("]")) {
					readingAirports = false;

				} else if (readingAirports && line.startsWith("\"")) {
					graph.addVertex(line.replace("\"", "").replace(",", "").trim());

				} else if (line.startsWith("{ \"origin\"")) {
					String origin = extractField(line, "origin").replace("\"", "");
					String destination = extractField(line, "destination").replace("\"", "");
					int distance = Integer.parseInt(extractField(line, "distance"));
					graph.addEdge(origin, destination, distance);
				}
			}

		} catch (IOException e) {
			System.out.println("No airports file found, using initial data.");
		}
	}

	private String extractField(String line, String fieldName) {

		int fieldIndex = line.indexOf("\"" + fieldName + "\"");
		int colonIndex = line.indexOf(":", fieldIndex);

		int commaIndex = line.indexOf(",", colonIndex);
		int braceIndex = line.indexOf("}", colonIndex);

		int endIndex;

		if (commaIndex == -1) {
			endIndex = braceIndex;
		} else {
			endIndex = Math.min(commaIndex, braceIndex);
		}

		return line.substring(colonIndex + 1, endIndex).trim();
	}
}