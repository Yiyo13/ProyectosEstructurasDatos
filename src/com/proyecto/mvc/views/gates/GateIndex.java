package com.proyecto.mvc.views.gates;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GateIndex extends JPanel {

	public JComboBox<String> cbFlight;
	public JButton btnAssign;
	public JTextArea textArea;

	public GateIndex() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel();
		add(top, BorderLayout.NORTH);

		top.add(new JLabel("Vuelos simultáneos para prueba:"));
		cbFlight = new JComboBox<>();
		top.add(cbFlight);

		btnAssign = new JButton("Ejecutar Backtracking");
		top.add(btnAssign);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
}