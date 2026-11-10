package com.proyecto.mvc.controllers;

import com.proyecto.mvc.models.TripLogic;
import com.proyecto.mvc.models.Stack;
import com.proyecto.mvc.models.Trip;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.trips.MyTripsIndex;

public class TripController {

	private ViewPrincipal vp;
	private Stack<Trip> tripStack;
	private TripLogic tripLogic;
	
	public TripController(ViewPrincipal vp, Stack<Trip> tripStack, TripLogic tripLogic) {
		this.vp = vp;
		this.tripStack = tripStack;
		this.tripLogic = tripLogic;
	}
	
	public void index() {
		
		MyTripsIndex v = new MyTripsIndex();
		v.textArea.setText(tripLogic.printAll(tripStack));
		
		v.btnRefresh.addActionListener(e -> {
			v.textArea.setText(tripLogic.printAll(tripStack));
		});
		
		vp.setContent(v, "Mis Viajes");
	}
}