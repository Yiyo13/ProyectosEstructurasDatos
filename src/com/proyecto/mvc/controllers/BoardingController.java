package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.BoardingLogic;
import com.proyecto.mvc.models.CircularDoubleList;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.NodePassenger;
import com.proyecto.mvc.models.Passenger;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.boarding.BoardingIndex;

public class BoardingController {

	private ViewPrincipal vp;
	private CircularDoubleList flights;
	private BoardingLogic boardingLogic;

	public BoardingController(ViewPrincipal vp, CircularDoubleList flights, BoardingLogic boardingLogic) {
		this.vp = vp;
		this.flights = flights;
		this.boardingLogic = boardingLogic;
	}

	public void index() {

		BoardingIndex v = new BoardingIndex();

		v.btnAddToQueue.addActionListener(e -> {

			try {

				Flight flight = flights.getCurrent();

				if (flight == null) {
					JOptionPane.showMessageDialog(null, "No hay un vuelo seleccionado");
					return;
				}

				int id = Integer.valueOf(v.tPassengerId.getText());
				Passenger passenger = findPassenger(flight, id);

				if (passenger == null) {
					JOptionPane.showMessageDialog(null, "El pasajero no tiene reserva en este vuelo");
					return;
				}

				boolean added = boardingLogic.addInQueue(flight.getBoardingQueue(), flight, passenger);

				if (added) {
					v.textArea.setText("Pasajero " + passenger.getNameP() + " agregado a la cola de abordaje");
				} else {
					JOptionPane.showMessageDialog(null, "El pasajero ya abordo o no se pudo agregar");
				}

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "El id del pasajero debe ser numerico");
			}
		});

		v.btnBoardNext.addActionListener(e -> {

			Flight flight = flights.getCurrent();

			if (flight == null) {
				JOptionPane.showMessageDialog(null, "No hay un vuelo seleccionado");
				return;
			}

			Passenger passenger = boardingLogic.boardNext(flight.getBoardingQueue());

			if (passenger == null) {
				v.textArea.setText("No hay pasajeros en la cola de abordaje");
			} else {
				v.textArea.setText("Abordo: " + passenger.getNameP());
			}
		});

		vp.setContent(v, "Cola de Abordaje");
	}

	private Passenger findPassenger(Flight flight, int id) {

		NodePassenger aux = flight.getSeats().getFirst();

		while (aux != null) {

			if (aux.getPassenger().getIdP() == id) {
				return aux.getPassenger();
			}

			aux = aux.getNext();
		}

		return null;
	}

}