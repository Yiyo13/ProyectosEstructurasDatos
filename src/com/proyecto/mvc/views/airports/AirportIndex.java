package com.proyecto.mvc.views.airports;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AirportIndex extends JPanel {
	public JTextField tName;
	public JButton btnAddAirport;
	public JComboBox<String> cbOrigin;
	public JComboBox<String> cbDestination;
	public JTextField tDistance;
	public JButton btnAddConnection;
	public DefaultListModel<String> listModel;
	public JList<String> listAirports;
	public JTextArea textArea;

	public AirportIndex() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel(new GridLayout(2, 1));
		add(top, BorderLayout.NORTH);

		JPanel rowAirport = new JPanel();
		rowAirport.setBorder(new TitledBorder("Registrar aeropuerto"));
		top.add(rowAirport);

		rowAirport.add(new JLabel("Ciudad"));
		tName = new JTextField(15);
		rowAirport.add(tName);

		btnAddAirport = new JButton("Registrar");
		rowAirport.add(btnAddAirport);

		JPanel rowConnection = new JPanel();
		rowConnection.setBorder(new TitledBorder("Registrar conexion (ida y vuelta)"));
		top.add(rowConnection);

		cbOrigin = new JComboBox<>();
		rowConnection.add(cbOrigin);

		cbDestination = new JComboBox<>();
		rowConnection.add(cbDestination);

		rowConnection.add(new JLabel("Km"));
		tDistance = new JTextField(6);
		rowConnection.add(tDistance);

		btnAddConnection = new JButton("Conectar");
		rowConnection.add(btnAddConnection);

		JPanel center = new JPanel(new GridLayout(1, 2, 5, 0));
		add(center, BorderLayout.CENTER);

		listModel = new DefaultListModel<>();
		listAirports = new JList<>(listModel);
		JScrollPane spList = new JScrollPane(listAirports);
		spList.setBorder(new TitledBorder("Aeropuertos"));
		center.add(spList);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane spText = new JScrollPane(textArea);
		spText.setBorder(new TitledBorder("Conexiones del seleccionado"));
		center.add(spText);
	}
}
