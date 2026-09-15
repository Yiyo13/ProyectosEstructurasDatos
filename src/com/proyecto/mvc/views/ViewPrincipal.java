package com.proyecto.mvc.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPrincipal extends JFrame {

	private JPanel contentPane;

	
	public ViewPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		
		JPanel bottomsPanel = new JPanel();
		panelMenu.add(bottomsPanel);
		bottomsPanel.setLayout(new GridLayout(4, 1, 0, 5));
		
		JButton btnControlTower = new JButton("Torre de Control");
		btnControlTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bottomsPanel.add(btnControlTower);
		
		JButton btnReservationsAndSeats = new JButton("Reservar y Asientos");
		bottomsPanel.add(btnReservationsAndSeats);
		
		JButton btnMyTrips = new JButton("Mis Viajes");
		bottomsPanel.add(btnMyTrips);
		
		JButton btnBoardingQueue = new JButton("Cola de Abordaje");
		bottomsPanel.add(btnBoardingQueue);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		panel.add(panelTitle, BorderLayout.NORTH);
		
		JLabel lblSistemaDeControl = new JLabel("Sistema de Control Aeroportuario");
		panelTitle.add(lblSistemaDeControl);
		
		JPanel contentPanel = new JPanel();
		panel.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
	}

}
