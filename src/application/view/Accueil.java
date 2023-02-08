package application.view;

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
		
		ComboBox<Integer> comboBoxJoueur = new ComboBox();
		comboBoxJoueur.setId("custom-combo-box-joueur");
		comboBoxJoueur.getItems().addAll(1,2,3,4);
		
		Label labelCarte = new Label("Choisissez le nombre de paire :");

		ComboBox comboBoxCarte = new ComboBox();
		comboBoxCarte.setId("custom-combo-box-carte");
		comboBoxCarte.getItems().addAll(2,5,10,15,20,25,30);
		comboBoxCarte.setValue(2);

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
			HBox hbox = new HBox();
			
			int row = 0;
			
			GridPane gridPane = new GridPane();
			
			gridPane.setPadding(new Insets(20));
			gridPane.setVgap(10);
			gridPane.setHgap(10);
			

			int paire = Integer.valueOf(comboBoxCarte.getValue().toString());
			
			Button[] boutonsTab = new Button[(paire*2)];
			for(int i = 0; i < (paire * 2); i++) {
				Button bouton = new Button();
				bouton.setMinSize(60, 80);
		        ImageView imageView = new ImageView(getClass().getResource("cart.png").toExternalForm());
				bouton.setGraphic(imageView);
				bouton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
		        	public void handle(ActionEvent event) {
		        		if (compteur <2) {
		        			bouton.setText("Hello, World.");
							bouton.setGraphic(null);
							compteur ++;
		        		}
		        		else {
		        			bouton.setText("");
							bouton.setGraphic(imageView);
							compteur = 0;
		        		}
		            	
		            }	            
		        });	
		      
				
				

				boutonsTab[i] =  bouton;
				if((i + 1) % 5 == 0) {
					gridPane.addRow(row, boutonsTab[i], boutonsTab[i - 1], boutonsTab[i - 2], boutonsTab[i - 3], boutonsTab[i - 4]);
					row++;
				}
				
			}
			
			
			Button btn = new Button();
	        btn.setText("Quit");
	        btn.setOnAction((ActionEvent e) -> {
	            Platform.exit();
	        });
	        
	        Label[] labelNom = new Label[comboBoxJoueur.getValue()];
			

			for(int i = 0;i < comboBoxJoueur.getValue();i++){
				labelNom[i] = new Label();
				labelNom[i].setId("custom-label");
				labelNom[i].setPrefSize(200, 50);
				labelNom[i].setText(textFields[i].getText()+" Score : ");
			}	
	        
	        VBox vboxTest = new VBox();
			vboxTest.getChildren().add(gridPane);
			

			VBox vboxTest2 = new VBox();
			vboxTest2.getChildren().add(btn);
			vboxTest2.getChildren().addAll(labelNom);
			vboxTest2.setSpacing(10);
			
			hbox.getChildren().add(vboxTest);

			hbox.getChildren().add(vboxTest2);	

			Scene secondScene = new Scene(hbox, 700, 400);
			secondScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


			// New window (Stage)
			Stage newWindow = new Stage();
			newWindow.setTitle("Second Stage");
			newWindow.setScene(secondScene);
			// Set position of second window, related to primary window.
			newWindow.setX(primaryStage.getX() + 200);
			newWindow.setY(primaryStage.getY() + 100);

			newWindow.show();
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
