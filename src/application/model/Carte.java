package application.model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Carte extends Button{
	
	private int value;
	
	private ImageView imageViewCarteFace;
	
	private ImageView imageViewCartePile;

	public Carte(int value, ImageView imageViewPile, ImageView imageViewFace) {
		super();
		this.value = value;
		this.imageViewCartePile = imageViewPile;
		this.imageViewCarteFace = imageViewFace;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
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
	
	public void retourner() {
		if(this.getGraphic() == this.imageViewCartePile) {
			this.setGraphic(imageViewCarteFace);
		}
		else {
			this.setGraphic(imageViewCartePile);
		}
	}
}
