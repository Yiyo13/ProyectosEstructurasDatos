package com.proyecto.mvc.views.flights;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import com.proyecto.mvc.models.Flight;

public class FlightForm extends JPanel {
	public JTextField tFlightNumber;
	public JComboBox<String> cbOrigin;
	public JComboBox<String> cbDestination;
	public JComboBox<String> cbPlane;
	public JTextField tMaximumCapcity;
	public JTextField tFuel;
	public JButton btnBack;
	public JButton btnSave;

	public FlightForm() {
		setLayout(null);

		JLabel lblNumeroDeVuelo = new JLabel("Numero de Vuelo");
		lblNumeroDeVuelo.setBounds(151, 14, 150, 16);
		add(lblNumeroDeVuelo);

		tFlightNumber = new JTextField();
		tFlightNumber.setBounds(151, 34, 213, 22);
		add(tFlightNumber);

		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(151, 66, 150, 16);
		add(lblOrigen);

		cbOrigin = new JComboBox<>();
		cbOrigin.setBounds(151, 86, 213, 22);
		add(cbOrigin);

		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(151, 118, 150, 16);
		add(lblDestino);

		cbDestination = new JComboBox<>();
		cbDestination.setBounds(151, 138, 213, 22);
		add(cbDestination);

		JLabel lblTipoDeAvion = new JLabel("Tipo de Avion");
		lblTipoDeAvion.setBounds(151, 170, 150, 16);
		add(lblTipoDeAvion);

		cbPlane = new JComboBox<>();
		cbPlane.setModel(new DefaultComboBoxModel<>(Flight.PLANES));
		cbPlane.setBounds(151, 190, 213, 22);
		add(cbPlane);

		JLabel lblCapacidadMaxima = new JLabel("Capacidad Maxima");
		lblCapacidadMaxima.setBounds(151, 222, 150, 16);
		add(lblCapacidadMaxima);

		tMaximumCapcity = new JTextField();
		tMaximumCapcity.setBounds(151, 242, 213, 22);
		add(tMaximumCapcity);

		JLabel lblCombustible = new JLabel("Combustible (minutos)");
		lblCombustible.setBounds(151, 274, 180, 16);
		add(lblCombustible);

		tFuel = new JTextField();
		tFuel.setBounds(151, 294, 213, 22);
		add(tFuel);

		btnSave = new JButton("Guardar");
		btnSave.setBounds(151, 344, 97, 25);
		add(btnSave);

		btnBack = new JButton("Regresar");
		btnBack.setBounds(267, 344, 97, 25);
		add(btnBack);
	}
}
