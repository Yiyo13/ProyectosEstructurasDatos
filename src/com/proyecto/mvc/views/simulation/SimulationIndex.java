package com.proyecto.mvc.views.simulation;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class SimulationIndex extends JPanel {

	public JButton btnStart;
	public JButton btnPause;
	public JButton btnReset;
	public JComboBox<String> cbAirport;
	public JLabel lblTime;
	public JLabel lblInfo;
	public DefaultTableModel inboundModel;
	public DefaultTableModel pendingModel;

	public SimulationIndex() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel();
		add(top, BorderLayout.NORTH);

		btnStart = new JButton("Iniciar");
		top.add(btnStart);
		btnPause = new JButton("Pausar");
		top.add(btnPause);
		btnReset = new JButton("Reiniciar");
		top.add(btnReset);

		lblTime = new JLabel("Tiempo: 0 s");
		lblTime.setFont(new Font("Dialog", Font.BOLD, 14));
		top.add(lblTime);

		top.add(new JLabel("Aeropuerto"));
		cbAirport = new JComboBox<>();
		top.add(cbAirport);

		JPanel tables = new JPanel(new GridLayout(1, 2, 5, 0));
		add(tables, BorderLayout.CENTER);

		inboundModel = new DefaultTableModel(new String[] { "Vuelo", "Ruta", "Restante (s)" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane spInbound = new JScrollPane(new JTable(inboundModel));
		spInbound.setBorder(new TitledBorder("En transito hacia el aeropuerto"));
		tables.add(spInbound);

		pendingModel = new DefaultTableModel(new String[] { "Vuelo", "Origen", "Combustible (min)" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane spPending = new JScrollPane(new JTable(pendingModel));
		spPending.setBorder(new TitledBorder("Pendientes de aterrizaje"));
		tables.add(spPending);

		lblInfo = new JLabel(" ");
		add(lblInfo, BorderLayout.SOUTH);
	}
}