package com.proyecto.mvc.views.routes;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RouteIndex extends JPanel {
	public JComboBox<String> cbOrigin;
	public JComboBox<String> cbDestination;
	public JButton btnDfs;
	public JButton btnDijkstra;
	public JTextArea textArea;

	public RouteIndex() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel();
		add(top, BorderLayout.NORTH);

		top.add(new JLabel("Origen"));
		cbOrigin = new JComboBox<>();
		top.add(cbOrigin);

		top.add(new JLabel("Destino"));
		cbDestination = new JComboBox<>();
		top.add(cbDestination);

		btnDfs = new JButton("DFS");
		top.add(btnDfs);

		btnDijkstra = new JButton("Dijkstra");
		top.add(btnDijkstra);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
}
