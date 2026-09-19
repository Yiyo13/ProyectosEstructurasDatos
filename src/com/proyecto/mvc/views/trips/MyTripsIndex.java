package com.proyecto.mvc.views.trips;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class MyTripsIndex extends JPanel {
	public JTextArea textArea;
	public JButton btnRefresh;

	public MyTripsIndex() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btnRefresh = new JButton("Actualizar");
		panel.add(btnRefresh);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		textArea = 	new JTextArea();
		textArea.setEditable(false);
		textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		centerPanel.add(textArea, BorderLayout.CENTER);
	}
}