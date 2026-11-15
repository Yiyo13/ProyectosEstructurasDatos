package com.proyecto.mvc.views.gates;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class GateIndex extends JPanel {

	public JComboBox<String> cbAirport;
	public JTextField tGateId;
	public JCheckBox chkSmall;
	public JCheckBox chkMedium;
	public JCheckBox chkLarge;
	public JButton btnAddGate;
	public JTextArea gatesArea;
	public DefaultListModel<String> flightsModel;
	public JList<String> listFlights;
	public JButton btnAssign;
	public JTextArea textArea;

	public GateIndex() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel(new GridLayout(2, 1));
		add(top, BorderLayout.NORTH);

		JPanel rowAirport = new JPanel();
		rowAirport.setBorder(new TitledBorder("Aeropuerto"));
		cbAirport = new JComboBox<>();
		rowAirport.add(cbAirport);
		top.add(rowAirport);

		JPanel rowGate = new JPanel();
		rowGate.setBorder(new TitledBorder("Registrar puerta en el aeropuerto seleccionado"));
		rowGate.add(new JLabel("Id"));
		tGateId = new JTextField(5);
		rowGate.add(tGateId);

		rowGate.add(new JLabel("Tamanos permitidos:"));
		chkSmall = new JCheckBox("Pequeno");
		rowGate.add(chkSmall);
		chkMedium = new JCheckBox("Mediano");
		rowGate.add(chkMedium);
		chkLarge = new JCheckBox("Grande");
		rowGate.add(chkLarge);

		btnAddGate = new JButton("Agregar puerta");
		rowGate.add(btnAddGate);
		top.add(rowGate);

		JPanel center = new JPanel(new GridLayout(1, 2, 5, 0));
		add(center, BorderLayout.CENTER);

		gatesArea = new JTextArea();
		gatesArea.setEditable(false);
		gatesArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane spGates = new JScrollPane(gatesArea);
		spGates.setBorder(new TitledBorder("Puertas del aeropuerto"));
		center.add(spGates);

		JPanel flightsPanel = new JPanel(new BorderLayout(0, 5));
		flightsModel = new DefaultListModel<>();
		listFlights = new JList<>(flightsModel);
		listFlights.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane spFlights = new JScrollPane(listFlights);
		spFlights.setBorder(new TitledBorder("Vuelos aterrizados (Ctrl + clic para elegir varios)"));
		flightsPanel.add(spFlights, BorderLayout.CENTER);

		btnAssign = new JButton("Ejecutar Backtracking");
		flightsPanel.add(btnAssign, BorderLayout.SOUTH);
		center.add(flightsPanel);

		textArea = new JTextArea(14, 40);
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane spTrace = new JScrollPane(textArea);
		spTrace.setBorder(new TitledBorder("Traza del Backtracking"));
		add(spTrace, BorderLayout.SOUTH);
	}
}
