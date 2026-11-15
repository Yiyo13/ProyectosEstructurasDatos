package com.proyecto.init;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.proyecto.mvc.controllers.PrincipalController;

public class Main {

	public static void main(String[] args) {
		FlatCarbonIJTheme.setup();
		
		new PrincipalController().init();
	}

}
