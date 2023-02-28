package application.view;


import java.util.Random;
import javafx.util.Duration;

import application.model.Carte;
import application.model.Joueur;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Grille {
	private Random rand = new Random();
	
	private int[] numbersValue;
	private int[] numbersInstall;
	private Carte[] boutonsTab;
	private Joueur[] joueurs;
	
	private int compteur;
	private Carte carteRetourne1 = null;
	private Carte carteRetourne2 = null;
	private int currentPlayerPosition;

	public Grille(Stage primaryStage ,ComboBox comboBoxCarte, ComboBox<Integer> comboBoxJoueur, TextField[] textFields) {
		super();
		
		HBox hbox = new HBox();
		
		GridPane gridPane = new GridPane();
		
		gridPane.setPadding(new Insets(20));
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		
		int paire = Integer.valueOf(comboBoxCarte.getValue().toString());
		
		boutonsTab = new Carte[(paire*2)];
		
		numbersValue = new int[paire];
		
		numbersInstall = new int[paire * 2];
		
		this.initTabInt(numbersValue, 30);
		this.initTabInt(numbersInstall, paire * 2);
		
		int j = 1;
		
		for(int c = 0; c < (paire * 2); c++) {
			Carte carte1 = new Carte(numbersValue[paire - j], new ImageView(getClass().getResource("carte_pile.png").toExternalForm()), new ImageView(getClass().getResource("carte_" + numbersValue[paire - j] + ".png").toExternalForm()));
			carte1.setMinSize(60, 80);
			carte1.setPadding(Insets.EMPTY);
			carte1.setGraphic(carte1.getImageViewCartePile());
			boutonsTab[numbersInstall[c]] = carte1;
			
			carte1.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                	handleCarteClick(event, carte1);
                }
            });
			
			c++;
			Carte carte2 = new Carte(numbersValue[paire - j], new ImageView(getClass().getResource("carte_pile.png").toExternalForm()), new ImageView(getClass().getResource("carte_" + numbersValue[paire - j] + ".png").toExternalForm()));
			carte2.setMinSize(60, 80);
			carte2.setPadding(Insets.EMPTY);
			carte2.setGraphic(carte2.getImageViewCartePile());
			boutonsTab[numbersInstall[c]] =  carte2;
			
			carte2.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                	handleCarteClick(event, carte2);
                }
            });
			
			j++;
		}
		
		int rows = 0;
		int columns = 0;
		
		int num = paire * 2;  // nombre aléatoire
	    int sqrt = (int) Math.sqrt(num);
	    for (int i = sqrt; i > 0; i--) {
	      if (num % i == 0) {
	        int divisor = i;
	        int multiple = num / divisor;
	        if (divisor != multiple) {
	        	rows = divisor;
	        	columns = multiple;
		        break;
	        }
	      }
	    }
	    
	    int boucle = 0;
	    
	    for(int r = 0; r < rows; r++) {
	    	for(int c = 0; c < columns; c++) {
	    		gridPane.add(boutonsTab[boucle], c, r);
	    		boucle++;
	    	}
	    }
		
		
		Button btn = new Button();
        btn.setText("Quit");
        btn.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });
        
        this.joueurs = new Joueur[comboBoxJoueur.getValue()];
		

        for(int i = 0;i < comboBoxJoueur.getValue();i++){
        	joueurs[i] = new Joueur(textFields[i].getText()+" Score : ");
        	joueurs[i].setId("custom-label");
        	joueurs[i].setPrefSize(200, 50);
		}
        
        this.currentPlayerPosition = 0;
        joueurs[currentPlayerPosition].setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        
        VBox vboxTest = new VBox();
		vboxTest.getChildren().add(gridPane);
		

		VBox vboxTest2 = new VBox();
		vboxTest2.getChildren().add(btn);
		vboxTest2.getChildren().addAll(joueurs);
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
	}
	
	private void initTabInt(int[] numbers, int range) {
        for (int i = 0; i < numbers.length; i++) {
            int next = rand.nextInt(range);
            for (int j = 0; j < i; j++) {
                if (numbers[j] == next) {
                    next = rand.nextInt(range);
                    j = -1;
                }
            }
            numbers[i] = next;
        }
	}
	
	public void handleCarteClick(ActionEvent event, Carte carte) {
		if(!ensembleCartesRertournes()) {
			if(carteRetourne1 == null) {
	    		carteRetourne1 = carte;
	    		carte.retourner();
	    		carte.setDisable(true);
	    	}
	    	else if(carteRetourne2 == null) {
	    		carteRetourne2 = carte;
	    		carte.retourner();
	    	}
	    	else {
				PauseTransition pause = new PauseTransition(Duration.seconds(1));
				pause.play();		
				pause.setOnFinished(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				    	if(carteRetourne1.getValue() == carteRetourne2.getValue()) {
				    		joueurs[currentPlayerPosition].ajoutPoint();
				    		joueurs[currentPlayerPosition].setText(joueurs[currentPlayerPosition].getNom() + joueurs[currentPlayerPosition].getScore());
				    		carteRetourne1.setDisable(true);
							carteRetourne2.setDisable(true);
							carteRetourne1 = null;
							carteRetourne2 = null;
						}
						else {
							if(joueurs.length > 1) {
								if(currentPlayerPosition == joueurs.length -1) {
									joueurs[currentPlayerPosition].setStyle("-fx-border-color: white; -fx-border-width: 0px;");
									currentPlayerPosition = 0;
									joueurs[currentPlayerPosition].setStyle("-fx-border-color: black; -fx-border-width: 2px;");
								}
								else {
									joueurs[currentPlayerPosition].setStyle("-fx-border-color: white; -fx-border-width: 0px;");
									currentPlayerPosition++;
									joueurs[currentPlayerPosition].setStyle("-fx-border-color: black; -fx-border-width: 2px;");
								}
							}
							carteRetourne1.retourner();
							carteRetourne2.retourner();
							carteRetourne1.setDisable(false);
							carteRetourne1 = null;
							carteRetourne2 = null;
						}
				    	compteur++;
				    }
				});
				/*System.out.println(carteRetourne1.getValue());
		    	System.out.println(carteRetourne2.getValue());
		    	System.out.println(currentPlayerPosition);
		    	System.out.println(joueurs[currentPlayerPosition].getScore());*/
			}
		}
    }
	
	public boolean ensembleCartesRertournes() {
		for(int i = 0; i < boutonsTab.length; i++) {
			if(boutonsTab[i].getGraphic() == boutonsTab[i].getImageViewCartePile()) {
				return false;
			}
		}
		return true;
	}
}
