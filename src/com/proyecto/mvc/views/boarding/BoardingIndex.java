package com.proyecto.mvc.views.boarding;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class BoardingIndex extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton btnAddToQueue;
	public JButton btnBoardNext;
	public JTextField tPassengerId;
	public JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public BoardingIndex() {

		setLayout(new BorderLayout(0, 0));

		JPanel bottomsPanel = new JPanel();
		add(bottomsPanel, BorderLayout.SOUTH);

		JLabel lblId = new JLabel("ID Pasajero");
		bottomsPanel.add(lblId);

		tPassengerId = new JTextField();
		tPassengerId.setColumns(10);
		bottomsPanel.add(tPassengerId);

		btnAddToQueue = new JButton("Agregar a cola");
		bottomsPanel.add(btnAddToQueue);

		btnBoardNext = new JButton("Abordar siguiente");
		bottomsPanel.add(btnBoardNext);

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0, 16, 20, 16));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		textArea.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPanel.add(textArea, BorderLayout.CENTER);
	}

}