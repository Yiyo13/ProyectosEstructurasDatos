package com.proyecto.mvc.controllers;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.AVLTree;
import com.proyecto.mvc.models.Flight;
import com.proyecto.mvc.models.FlightLogic;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.avl.AVLExample;
import com.proyecto.mvc.views.avl.AVLIndex;

public class AVLController {

	private ViewPrincipal vp;
	private AVLTree avl;
	private FlightLogic flightLogic;

	public AVLController(ViewPrincipal vp, AVLTree avl, FlightLogic flightLogic) {
		this.vp = vp;
		this.avl = avl;
		this.flightLogic = flightLogic;
	}

	private int readNumber(AVLIndex v) {
		try {
			return Integer.parseInt(v.tFlightNumber.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El numero de vuelo debe ser un numero");
			return -1;
		}
	}

	private void showTraversal(AVLIndex v, String name, String traversal) {

		v.listModel.clear();

		if (!traversal.isEmpty()) {
			String[] numbers = traversal.split(" ");
			for (int i = 0; i < numbers.length; i++) {
				v.listModel.addElement(Integer.parseInt(numbers[i]));
			}
		}

		v.lblTraversal.setText(name + " (click para ver vuelo)");
		v.lblLog.setText(name + ": " + traversal.replace(" ", " - "));
	}

	public void index() {

		AVLIndex v = new AVLIndex();
		v.treePanel.setTree(avl);

		v.btnSearch.addActionListener(e -> {

			int number = readNumber(v);
			if (number == -1) {
				return;
			}

			Flight flight = avl.search(number);

			if (flight == null) {
				v.textArea.setText("No existe el vuelo " + number);
				v.treePanel.setHighlight(-1);
			} else {
				v.textArea.setText(flight.toString());
				v.treePanel.setHighlight(number);
			}
		});

		v.btnDelete.addActionListener(e -> {

			int number = readNumber(v);
			if (number == -1) {
				return;
			}

			String error = flightLogic.delete(number);

			if (error != null) {
				JOptionPane.showMessageDialog(null, error);
				return;
			}

			v.textArea.setText("Vuelo " + number + " eliminado");
			v.lblLog.setText(avl.getLog().isEmpty() ? "Sin rotaciones" : avl.getLog().replace("\n", "  "));
			v.treePanel.setTree(avl);
		});

		v.btnPreorder.addActionListener(e -> {
			showTraversal(v, "Preorden", avl.preorder());
		});

		v.btnInorder.addActionListener(e -> {
			showTraversal(v, "Inorden", avl.inorder());
		});

		v.btnPostorder.addActionListener(e -> {
			showTraversal(v, "Postorden", avl.postorder());
		});

		v.listTraversal.addListSelectionListener(e -> {

			Integer number = v.listTraversal.getSelectedValue();

			if (number != null && !e.getValueIsAdjusting()) {
				v.textArea.setText(avl.search(number).toString());
				v.treePanel.setHighlight(number);
			}
		});

		v.btnExamples.addActionListener(e -> {
			examples();
		});

		vp.setContent(v, "Arbol AVL de Vuelos");
	}

	public void examples() {

		AVLExample v = new AVLExample();
		v.tSequence.setText("30, 20, 10");

		v.cbExample.addActionListener(e -> {
			String selected = (String) v.cbExample.getSelectedItem();
			v.tSequence.setText(selected.substring(selected.lastIndexOf(":") + 1).trim());
		});

		v.btnRun.addActionListener(e -> {

			String[] parts = v.tSequence.getText().split(",");

			if (parts.length < 2) {
				JOptionPane.showMessageDialog(null, "Ingrese al menos dos numeros separados por coma");
				return;
			}

			AVLTree before = new AVLTree();// arbol de prueba
			AVLTree after = new AVLTree();
			String text = "";

			try {

				for (int i = 0; i < parts.length; i++) {

					int number = Integer.parseInt(parts[i].trim());
					Flight flight = new Flight(number, "-", "-", "Boeing 737", 1, 1, Flight.SCHEDULED);

					if (i < parts.length - 1) {
						before.insert(flight);
					}

					if (!after.insert(flight)) {
						text += "Insertar " + number + ": repetido, se ignora\n";
						continue;
					}

					text += "Insertar " + number + ": " + (after.getLog().isEmpty() ? "sin rotacion\n" : after.getLog());
				}

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "La secuencia debe tener solo numeros");
				return;
			}

			v.treeBefore.setTree(before);
			v.treeAfter.setTree(after);
			v.textArea.setText(text + "\nUltima insercion: vuelo " + parts[parts.length - 1].trim());
		});

		v.btnBack.addActionListener(e -> {
			index();
		});

		v.btnRun.doClick();
		vp.setContent(v, "Ejemplos de Rotacion AVL");
	}
}