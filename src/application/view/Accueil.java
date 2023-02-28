package application.view;

import application.model.Joueur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Accueil {
	
	private VBox vbox;
	private int compteur;
	

	public Accueil(Stage primaryStage) {
		super();
		
		vbox = new VBox();
		VBox vbox2 = new VBox();

		
		Label labelJoueur = new Label("Choisissez le nombre de joueur :");
		
		ComboBox<Integer> comboBoxJoueur = new ComboBox<Integer>();
		comboBoxJoueur.setId("custom-combo-box-joueur");
		comboBoxJoueur.getItems().addAll(1,2,3,4);
		
		Label labelCarte = new Label("Choisissez le nombre de paire :");

		ComboBox<Integer> comboBoxCarte = new ComboBox<Integer>();
		comboBoxCarte.setId("custom-combo-box-carte");
		comboBoxCarte.getItems().addAll(5,10,15,20,25,30);
		comboBoxCarte.setValue(5);

		Label label3 = new Label("Noms des joueurs :");
        TextField[] textFields = new TextField[4];
       
        for (int i = 0; i < 4; i++) {
            textFields[i] = new TextField();
            textFields[i].setPromptText("Joueur " + (i + 1));
            textFields[i].setId("custom-text-field");
            
            if (i ==0) {
		        textFields[i].setEditable(true);
		        textFields[i].setVisible(true);
	        } else {
		        textFields[i].setEditable(false);
		        textFields[i].setVisible(false);
	        }
        }
		
			        
		comboBoxJoueur.setValue(1);
		comboBoxJoueur.setOnAction(e -> {
        int selected = comboBoxJoueur.getValue();
        for (int i = 0; i < textFields.length; i++) {
	        if (i < selected) {
		        textFields[i].setEditable(true);
		        textFields[i].setVisible(true);
	        } else {
		        textFields[i].setEditable(false);
		        textFields[i].setVisible(false);
		        textFields[i].setText(null);
	        }
	    }
        });
        
		Button button= new Button("Continuer");
		button.setOnAction((ActionEvent event)-> {
			Grille grille = new Grille(primaryStage, comboBoxCarte, comboBoxJoueur, textFields);
		});
	
		vbox.getChildren().add(labelJoueur);
		vbox.getChildren().add(comboBoxJoueur);
		vbox.getChildren().add(labelCarte);
		vbox.getChildren().add(comboBoxCarte);
		vbox.getChildren().add(label3);
		vbox.getChildren().addAll(textFields);
		vbox.getChildren().add(button);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
	}

	public VBox getVbox() {
		return vbox;
	}

	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}
}
