package application.model;

import javafx.scene.control.Button;

public class Carte extends Button{
	
	private String value;

	public Carte(String value) {
		super();
		this.value = value;
		this.setText(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
