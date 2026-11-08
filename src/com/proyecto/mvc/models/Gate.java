package com.proyecto.mvc.models;

public class Gate {

	private String id;
	private String[] allowedSizes;
	
	public Gate(String id, String[] allowedSizes) {
		this.id = id;
		this.allowedSizes = allowedSizes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getAllowedSizes() {
		return allowedSizes;
	}

	public void setAllowedSizes(String[] allowedSizes) {
		this.allowedSizes = allowedSizes;
	}
	
	public boolean isCompatible(String planeSize) {
		
		for (int i = 0; i < allowedSizes.length; i++) {
			if(allowedSizes[i].equalsIgnoreCase(planeSize)) {
				return true;
			}
		}
		
		return false;
	}

}
