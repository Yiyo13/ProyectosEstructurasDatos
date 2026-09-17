package com.proyecto.mvc.views.reservations;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class ReservationIndex extends JPanel {
	public JButton btnStart;
	public JButton btnEnd;
	public JButton btnReservation;
	private JPanel topContentPanel;
	private JTextArea textArea;
	private JComboBox comboBox;
	private JLabel lblVuelos;

	/**
	 * Create the panel.
	 */
	public ReservationIndex() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel bottomsPanel = new JPanel();
		add(bottomsPanel, BorderLayout.SOUTH);
		
		btnStart = new JButton("inicio/fin");
		bottomsPanel.add(btnStart);
		
		btnEnd = new JButton("fin/inicio");
		bottomsPanel.add(btnEnd);
		
		btnReservation = new JButton("Reservar");
		bottomsPanel.add(btnReservation);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 16, 20, 16));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		topContentPanel = new JPanel();
		topContentPanel.setBorder(new EmptyBorder(15, 75, 15, 80));
		contentPanel.add(topContentPanel, BorderLayout.NORTH);
		topContentPanel.setLayout(new BorderLayout(10, 0));
		
		comboBox = new JComboBox();
		topContentPanel.add(comboBox);
		
		lblVuelos = new JLabel("Vuelos");
		topContentPanel.add(lblVuelos, BorderLayout.WEST);
		
		textArea = new JTextArea();
		contentPanel.add(textArea, BorderLayout.CENTER);

	}

}
