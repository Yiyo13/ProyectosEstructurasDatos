package com.proyecto.mvc.views.flights;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class FlightForm extends JPanel {
	public JTextField tFlightNumber;
	public JTextField tRoute;
	public JTextField tMaximumCapcity;
	public JComboBox cbPlane;
	public JButton btnBack;
	public JButton btnSave;
	public JComboBox cbStatus;

	
	public FlightForm() {
		setLayout(null);
		
		JLabel lblNumeroDeVuelo = new JLabel("Numero de Vuelo");
		lblNumeroDeVuelo.setBounds(151, 24, 114, 16);
		add(lblNumeroDeVuelo);
		
		tFlightNumber = new JTextField();
		tFlightNumber.setBounds(151, 53, 213, 22);
		add(tFlightNumber);
		tFlightNumber.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ruta");
		lblNewLabel.setBounds(161, 88, 56, 16);
		add(lblNewLabel);
		
		tRoute = new JTextField();
		tRoute.setBounds(151, 117, 215, 22);
		add(tRoute);
		tRoute.setColumns(10);
		
		JLabel lblTipoDeAvion = new JLabel("Tipo de Avion");
		lblTipoDeAvion.setBounds(151, 152, 124, 16);
		add(lblTipoDeAvion);
		
		cbPlane = new JComboBox();
		cbPlane.setModel(new DefaultComboBoxModel(new String[] {"Boeing 737", "Airbus A320", "Boeing 787"}));
		cbPlane.setBounds(151, 185, 213, 22);
		add(cbPlane);
		
		JLabel lblCapacidadMaxima = new JLabel("Capacidad Maxima");
		lblCapacidadMaxima.setBounds(151, 220, 124, 16);
		add(lblCapacidadMaxima);
		
		tMaximumCapcity = new JTextField();
		tMaximumCapcity.setBounds(149, 249, 215, 22);
		add(tMaximumCapcity);
		tMaximumCapcity.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(151, 281, 56, 16);
		add(lblEstado);
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Disponible", "Ocupado"}));
		cbStatus.setBounds(151, 310, 213, 22);
		add(cbStatus);
		
		btnSave = new JButton("Guardar");
		btnSave.setBounds(151, 374, 97, 25);
		add(btnSave);
		
		btnBack = new JButton("Regresar");
		btnBack.setBounds(267, 374, 97, 25);
		add(btnBack);

	}
}
