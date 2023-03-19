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
	
	// Déclaration des variables de classe
	private VBox vbox;
	private HBox hbox;
	private int compteur;
	

	// Constructeur de la classe Accueil
	public Accueil(Stage primaryStage) {
		super();
		
		// Appel de la méthode AllImagesCheckboxs de la classe Bibliotheque
		Bibliotheque.AllImagesCheckboxs();
		
		// Initialisation des objets de la scène
		vbox = new VBox();
		VBox vbox2 = new VBox();

		hbox = new HBox();
		Button buttonBiblio = new Button("Bibliothéque");
		
		// Définition de l'action lors du clic sur le bouton "Bibliothéque"
		buttonBiblio.setOnAction((ActionEvent event)-> {
			new Bibliotheque(primaryStage);
		});
		
		// Ajout du bouton "Bibliothéque" dans la HBox
		hbox.getChildren().add(buttonBiblio);
		
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
       
        // Boucle pour créer les champs de texte pour les noms des joueurs
        for (int i = 0; i < 4; i++) {
            textFields[i] = new TextField();
            textFields[i].setPromptText("Joueur " + (i + 1));
            textFields[i].setId("custom-text-field");
            
            // Si c'est le premier joueur, le champ est éditable et visible
            // Sinon, les autres champs ne sont pas éditables et ne sont pas visibles
            if (i ==0) {
		        textFields[i].setEditable(true);
		        textFields[i].setVisible(true);
	        } else {
		        textFields[i].setEditable(false);
		        textFields[i].setVisible(false);
	        }
        }
		
			        
        // Définition de l'action à effectuer lors du changement de valeur de la ComboBox "comboBoxJoueur"
		comboBoxJoueur.setValue(1);
		comboBoxJoueur.setOnAction(e -> {
        int selected = comboBoxJoueur.getValue();
        
        // Boucle pour afficher/cacher les champs de texte en fonction du nombre de joueurs choisi
        for (int i = 0; i < textFields.length; i++) {
        	// Si l'index actuel du champ de texte est inférieur à la valeur sélectionnée
	        if (i < selected) {
	        	// Active et rend visible le champ de texte actuel
		        textFields[i].setEditable(true);
		        textFields[i].setVisible(true);
	        } else {
	        	// Désactive, masque et efface le champ de texte actuel
		        textFields[i].setEditable(false);
		        textFields[i].setVisible(false);
		        textFields[i].setText(null);
	        }
	    }
        });
        
		Button button= new Button("Continuer");
		button.setOnAction((ActionEvent event)-> {
			// Vérifie si la taille de la liste des visuels dans la bibliothèques est supérieure ou égale à la valeur sélectionnée dans la liste déroulante
			if(Bibliotheque.visuels.size() >= comboBoxCarte.getValue()) {
				// Crée un nouvel objet "Grille" et ferme la fenêtre principale
				Grille grille = new Grille(primaryStage, comboBoxCarte, comboBoxJoueur, textFields);
				primaryStage.close();
			}
			else {
				// Crée une fenêtre contextuelle pour afficher que le joueur n'a pas sélectionné dans la biliothèque un nombre de carte suffisant par rapport aux nombre de paires voulu
				Popup popup = new Popup();
	            popup.confirmation("Bibliothèques", "Il n'y a pas assez de cartes sélectionnées.", "OK", primaryStage, primaryStage);
			}
		});
		
		// Ajoute des éléments d'interface utilisateur à une boîte verticale
		vbox.getChildren().add(hbox);
		vbox.getChildren().add(labelJoueur);
		vbox.getChildren().add(comboBoxJoueur);
		vbox.getChildren().add(labelCarte);
		vbox.getChildren().add(comboBoxCarte);
		vbox.getChildren().add(label3);
		vbox.getChildren().addAll(textFields);
		vbox.getChildren().add(button);
		
		// Définit l'espacement entre les éléments et l'alignement de la boîte verticale
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
