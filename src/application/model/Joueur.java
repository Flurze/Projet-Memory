package application.model;

import javafx.scene.control.Label;

public class Joueur extends Label {
	
	private String nom;
	
	private int score;

	public Joueur(String nom) {
		super();
		this.nom = nom;
		this.setText(nom + score);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void ajoutPoint() {
		this.score++;
	}
}
