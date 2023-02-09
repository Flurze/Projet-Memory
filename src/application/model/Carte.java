package application.model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Carte extends Button{
	
	private String value;
	
	private ImageView imageViewCarteFace;
	
	private ImageView imageViewCartePile;

	public Carte(String value, ImageView imageViewPile, ImageView imageViewFace) {
		super();
		this.value = value;
		this.imageViewCartePile = imageViewPile;
		this.imageViewCarteFace = imageViewFace;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ImageView getImageViewCarteFace() {
		return imageViewCarteFace;
	}

	public void setImageViewCarteFace(ImageView imageViewCarteFace) {
		this.imageViewCarteFace = imageViewCarteFace;
	}

	public ImageView getImageViewCartePile() {
		return imageViewCartePile;
	}

	public void setImageViewCartePile(ImageView imageViewCartePile) {
		this.imageViewCartePile = imageViewCartePile;
	}
}
