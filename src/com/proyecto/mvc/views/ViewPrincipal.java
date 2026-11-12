package com.proyecto.mvc.views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

public class ViewPrincipal extends JFrame {

	public JPanel contentPane;
	public JButton btnFlights;
	public JButton btnReservationsAndSeats;
	public JButton btnMyTrips;
	public JButton btnBoardingQueue;
	public JButton btnAVL;
	public JButton btnAirports;
	public JButton btnRoutes;
	public JLabel lblTitle;
	public JPanel contentPanel;
	public JButton btnLanding;
	public JButton btnBoardingGates;
	public JButton btnSimulation;// CAMBIO 1: boton nuevo. Antes no existir, por eso simulacion no se poder abrir

	public ViewPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);

		JPanel bottomsPanel = new JPanel();
		panelMenu.add(bottomsPanel);
		bottomsPanel.setLayout(new GridLayout(10, 1, 0, 5)/* CAMBIO 2: antes 9 filas. Ahora 10 porque cueva tener un boton mas */);

		btnFlights = new JButton("Vuelos");
		bottomsPanel.add(btnFlights);

		btnReservationsAndSeats = new JButton("Reservar y Asientos");
		bottomsPanel.add(btnReservationsAndSeats);

		btnMyTrips = new JButton("Mis Viajes");
		bottomsPanel.add(btnMyTrips);

		btnBoardingQueue = new JButton("Cola de Abordaje");
		bottomsPanel.add(btnBoardingQueue);

		btnAVL = new JButton("Arbol AVL");
		bottomsPanel.add(btnAVL);

		btnAirports = new JButton("Aeropuertos");
		bottomsPanel.add(btnAirports);

		btnLanding = new JButton("Priorizar Aterrizaje");
		bottomsPanel.add(btnLanding);

		btnRoutes = new JButton("Rutas");
		bottomsPanel.add(btnRoutes);

		btnBoardingGates = new JButton("Puertas de embarque");
		bottomsPanel.add(btnBoardingGates);

		// CAMBIO 3: cavernicola crear boton, ponerle nombre y meter en cueva (panel) con los otros
		btnSimulation = new JButton("Simulacion");
		bottomsPanel.add(btnSimulation);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelTitle = new JPanel();
		panel.add(panelTitle, BorderLayout.NORTH);

		lblTitle = new JLabel("Sistema de Control Aeroportuario");
		panelTitle.add(lblTitle);

		contentPanel = new JPanel();
		panel.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
	}

	public void init() {
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle(" ");
	}

	public void setContent(JComponent c, String title) {
		setTitle(" " + title);
		lblTitle.setText(title);

		contentPanel.removeAll();
		contentPanel.add(c, BorderLayout.CENTER);

		contentPanel.repaint();
		contentPanel.revalidate();
	}
}