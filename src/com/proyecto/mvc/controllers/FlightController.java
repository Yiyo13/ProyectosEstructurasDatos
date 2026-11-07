package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.CircularDoubleList;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightLogic;
import com.proyecto.mvc.models.Graph;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.flights.FlightForm;
import com.proyecto.mvc.views.flights.FlightIndex;

public class FlightController {

	private ViewPrincipal vp;
	private CircularDoubleList flights;
	private FlightLogic flightLogic;
	private Graph graph;

	public FlightController(ViewPrincipal vp, CircularDoubleList flights, FlightLogic flightLogic, Graph graph) {
		this.vp = vp;
		this.flights = flights;
		this.flightLogic = flightLogic;
		this.graph = graph;
	}

	private FlightForm loadForm() {

		FlightForm form = new FlightForm();
		String[] names = graph.getNames();

		for (int i = 0; i < names.length; i++) {
			form.cbOrigin.addItem(names[i]);
			form.cbDestination.addItem(names[i]);
		}

		form.btnBack.addActionListener(e->{
			index();
		});

		return form;
	}

	public void create() {

		FlightForm form = loadForm();

		form.btnSave.addActionListener(e->{

			String origin = (String) form.cbOrigin.getSelectedItem();
			String destination = (String) form.cbDestination.getSelectedItem();

			String error = flightLogic.validate(origin, destination, form.tMaximumCapcity.getText(), form.tFuel.getText());

			if(error != null) {
				JOptionPane.showMessageDialog(null, error);
				return;
			}

			try {

				int flightNumber = Integer.parseInt(form.tFlightNumber.getText().trim());
				String plane = (String) form.cbPlane.getSelectedItem();
				int maximumCapacity = Integer.parseInt(form.tMaximumCapcity.getText().trim());
				int fuel = Integer.parseInt(form.tFuel.getText().trim());

				Flight flight = new Flight(flightNumber, origin, destination, plane, maximumCapacity, fuel, Flight.SCHEDULED);
				error = flightLogic.register(flight);

				if(error != null) {
					JOptionPane.showMessageDialog(null, error);
					return;
				}

				flights.selectFlight(flightNumber);
				index();

			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "El numero de vuelo debe ser un numero");
			}

		});

		vp.setContent(form, "Registrar Vuelo");
	}

	public void edit() {

		Flight flight = flights.getCurrent();

		if(flight == null) {
			JOptionPane.showMessageDialog(null, "No hay vuelos registrados");
			return;
		}

		FlightForm form = loadForm();

		form.tFlightNumber.setText(String.valueOf(flight.getFlightNumber()));
		form.tFlightNumber.setEnabled(false);// clave avl
		form.cbOrigin.setSelectedItem(flight.getOrigin());
		form.cbDestination.setSelectedItem(flight.getDestination());
		form.cbPlane.setSelectedItem(flight.getPlaneType());
		form.tMaximumCapcity.setText(String.valueOf(flight.getMaximumCapacity()));
		form.tFuel.setText(String.valueOf(flight.getFuel()));

		form.btnSave.addActionListener(e->{

			String origin = (String) form.cbOrigin.getSelectedItem();
			String destination = (String) form.cbDestination.getSelectedItem();

			String error = flightLogic.validate(origin, destination, form.tMaximumCapcity.getText(), form.tFuel.getText());

			if(error == null) {
				error = flightLogic.update(flight, origin, destination, (String) form.cbPlane.getSelectedItem(),
						Integer.parseInt(form.tMaximumCapcity.getText().trim()), Integer.parseInt(form.tFuel.getText().trim()));
			}

			if(error != null) {
				JOptionPane.showMessageDialog(null, error);
				return;
			}

			index();
		});

		vp.setContent(form, "Editar Vuelo");
	}

	private void show(FlightIndex v, Flight flight) {

		if(flight == null) {
			v.textArea.setText("No hay vuelos registrados");
		}else {
			v.textArea.setText(flight.toString());
		}
	}

	public void index() {

		FlightIndex v = new FlightIndex();

		show(v, flights.getCurrent());

		v.btnPrevious.addActionListener(e->{
			show(v, flights.getPrevious());
		});

		v.btnNext.addActionListener(e->{
			show(v, flights.getNext());
		});

		v.btnRegister.addActionListener(e->{
			create();
		});

		v.btnEdit.addActionListener(e->{
			edit();
		});

		v.btnDelete.addActionListener(e->{

			Flight flight = flights.getCurrent();

			if(flight == null) {
				return;
			}

			int option = JOptionPane.showConfirmDialog(null, "Eliminar el vuelo " + flight.getFlightNumber() + "?");

			if(option != JOptionPane.YES_OPTION) {
				return;
			}

			String error = flightLogic.delete(flight.getFlightNumber());

			if(error != null) {
				JOptionPane.showMessageDialog(null, error);
				return;
			}

			show(v, flights.getCurrent());
		});

		v.btnPrioritizeFlights.addActionListener(e->{

			if(flights.isEmpty()) {
				return;
			}

			flights.prioritizeFlights();
			flights.selectFlight(flights.getHead().getFlight().getFlightNumber());
			show(v, flights.getCurrent());
		});

		vp.setContent(v, "Lista de Vuelos");
	}

}
