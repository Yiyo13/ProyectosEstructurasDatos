package com.proyecto.mvc.views.gates;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GateIndex extends JPanel {

	public JTextField tFlights;
	public JButton btnAssign;
	public JTextArea textArea;

	public GateIndex() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel(new GridLayout(3, 1));
		add(top, BorderLayout.NORTH);

		
		JPanel rowGates = new JPanel();
		rowGates.setBorder(new TitledBorder("Puertas Disponibles"));
		rowGates.add(new JLabel("P1 (Pequeno/Mediano) | P2 (Mediano/Grande) | P3 (Grande)"));
		top.add(rowGates);

		
		JPanel rowFlights = new JPanel();
		rowFlights.setBorder(new TitledBorder("Ingrese los vuelos (separados por coma)"));
		rowFlights.add(new JLabel("Vuelos:"));
		tFlights = new JTextField(20);
		tFlights.setText("57825, 12345, 46714");
		rowFlights.add(tFlights);

		btnAssign = new JButton("Ejecutar Backtracking");
		rowFlights.add(btnAssign);
		top.add(rowFlights);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
}