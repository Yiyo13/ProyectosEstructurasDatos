package com.proyecto.mvc.views.avl;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class AVLExample extends JPanel {
	public JComboBox<String> cbExample;
	public JTextField tSequence;
	public JButton btnRun;
	public JButton btnBack;
	public TreePanel treeBefore;
	public TreePanel treeAfter;
	public JTextArea textArea;

	public AVLExample() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel();
		add(top, BorderLayout.NORTH);

		cbExample = new JComboBox<>(new String[] {
				"RSD: 30, 20, 10",
				"RSI: 10, 20, 30",
				"RDD (izquierda-derecha): 10, 8, 9",
				"RDI (derecha-izquierda): 10, 18, 12"});
		top.add(cbExample);

		top.add(new JLabel("Secuencia"));
		tSequence = new JTextField(14);
		top.add(tSequence);

		btnRun = new JButton("Ejecutar");
		top.add(btnRun);

		btnBack = new JButton("Regresar");
		top.add(btnBack);

		JPanel trees = new JPanel(new GridLayout(1, 2, 5, 0));
		add(trees, BorderLayout.CENTER);

		treeBefore = new TreePanel();
		JScrollPane spBefore = new JScrollPane(treeBefore);
		spBefore.setBorder(new TitledBorder("Antes de la ultima insercion"));
		trees.add(spBefore);

		treeAfter = new TreePanel();
		JScrollPane spAfter = new JScrollPane(treeAfter);
		spAfter.setBorder(new TitledBorder("Despues (balanceado)"));
		trees.add(spAfter);

		textArea = new JTextArea(7, 40);
		textArea.setEditable(false);
		add(new JScrollPane(textArea), BorderLayout.SOUTH);
	}
}
