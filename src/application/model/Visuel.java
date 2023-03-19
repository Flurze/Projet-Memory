package application.model;

import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;

public class Visuel extends CheckBox {
	
	private Image visuel;

	public Visuel(Image visuel, Boolean status) {
		super();
		this.visuel = visuel;
		this.setSelected(status);
	}
	
	public void check() {
		this.setSelected(true);
	}
	
	public void uncheck() {
		this.setSelected(false);
	}
	
	public Image getImage() {
		return this.visuel;
	}
}
