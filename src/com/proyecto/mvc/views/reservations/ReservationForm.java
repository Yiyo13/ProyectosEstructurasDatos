package com.proyecto.mvc.views.reservations;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReservationForm extends JPanel {
	public JTextField tIdentification;
	public JTextField tName;
	public JTextField tAge;
	public JButton btnSave;
	public JButton btnCancel;

	/**
	 * Create the panel.
	 */
	public ReservationForm() {
		setLayout(null);
		
		JLabel lblIdentificacion = new JLabel("Identificacion");
		lblIdentificacion.setBounds(133, 13, 90, 16);
		add(lblIdentificacion);
		
		tIdentification = new JTextField();
		tIdentification.setBounds(133, 42, 231, 22);
		add(tIdentification);
		tIdentification.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre Completo");
		lblNombre.setBounds(133, 77, 140, 16);
		add(lblNombre);
		
		tName = new JTextField();
		tName.setBounds(133, 98, 231, 22);
		add(tName);
		tName.setColumns(10);
		
		JLabel lblEdad = new JLabel("edad");
		lblEdad.setBounds(133, 133, 56, 16);
		add(lblEdad);
		
		tAge = new JTextField();
		tAge.setBounds(133, 156, 231, 22);
		add(tAge);
		tAge.setColumns(10);
		
		btnSave = new JButton("Reservado");
		btnSave.setBounds(141, 215, 97, 25);
		add(btnSave);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(250, 215, 97, 25);
		add(btnCancel);

	}
}
