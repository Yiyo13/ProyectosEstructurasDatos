package com.proyecto.mvc.views.landings;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LandingIndex extends JPanel {

	public JComboBox<String> cbAirport;
	public JButton btnExecute;
	public JTextArea textArea;

	public LandingIndex() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel();
		add(top, BorderLayout.NORTH);

		top.add(new JLabel("Aeropuerto de Destino"));
		cbAirport = new JComboBox<>();
		top.add(cbAirport);

		btnExecute = new JButton("Priorizar Aterrizaje");
		top.add(btnExecute);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
}