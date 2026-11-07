package com.proyecto.mvc.views.avl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AVLIndex extends JPanel {
	public JTextField tFlightNumber;
	public JButton btnSearch;
	public JButton btnDelete;
	public JButton btnPreorder;
	public JButton btnInorder;
	public JButton btnPostorder;
	public JButton btnExamples;
	public JLabel lblTraversal;
	public JLabel lblLog;
	public DefaultListModel<Integer> listModel;
	public JList<Integer> listTraversal;
	public JTextArea textArea;
	public TreePanel treePanel;

	public AVLIndex() {
		setLayout(new BorderLayout(0, 5));

		JPanel top = new JPanel(new GridLayout(2, 1));
		add(top, BorderLayout.NORTH);

		JPanel row1 = new JPanel();
		top.add(row1);

		row1.add(new JLabel("Numero de vuelo"));
		tFlightNumber = new JTextField(8);
		row1.add(tFlightNumber);

		btnSearch = new JButton("Buscar");
		row1.add(btnSearch);

		btnDelete = new JButton("Eliminar");
		row1.add(btnDelete);

		btnExamples = new JButton("Ejemplos de rotacion");
		row1.add(btnExamples);

		JPanel row2 = new JPanel();
		top.add(row2);

		btnPreorder = new JButton("Preorden");
		row2.add(btnPreorder);

		btnInorder = new JButton("Inorden");
		row2.add(btnInorder);

		btnPostorder = new JButton("Postorden");
		row2.add(btnPostorder);

		treePanel = new TreePanel();

		JPanel right = new JPanel(new BorderLayout(0, 5));
		right.setPreferredSize(new Dimension(260, 200));

		lblTraversal = new JLabel("Recorrido");
		right.add(lblTraversal, BorderLayout.NORTH);

		listModel = new DefaultListModel<>();
		listTraversal = new JList<>(listModel);
		right.add(new JScrollPane(listTraversal), BorderLayout.CENTER);

		textArea = new JTextArea(10, 20);
		textArea.setEditable(false);
		right.add(new JScrollPane(textArea), BorderLayout.SOUTH);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(treePanel), right);
		split.setResizeWeight(1);
		add(split, BorderLayout.CENTER);

		lblLog = new JLabel(" ");
		add(lblLog, BorderLayout.SOUTH);
	}
}
