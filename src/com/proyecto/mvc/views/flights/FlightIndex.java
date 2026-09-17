package com.proyecto.mvc.views.flights;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class FlightIndex extends JPanel {
	public JButton btnNext;
	public JButton btnPrevious;
	public JTextArea textArea;
	public JPanel panel;
	public JButton btnPrioritizeFlights;
	public JButton btnRegister;

	public FlightIndex() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btnPrevious = new JButton("Anterior");
		panel.add(btnPrevious);
		
		btnNext = new JButton("Siguiente");
		panel.add(btnNext);
		
		btnPrioritizeFlights = new JButton("Priorizar Vuelos");
		panel.add(btnPrioritizeFlights);
		
		btnRegister = new JButton("Registrar");
		panel.add(btnRegister);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setBorder(new EmptyBorder(0, 0, 0, 0));
		centerPanel.add(textArea, BorderLayout.CENTER);

	}

}
