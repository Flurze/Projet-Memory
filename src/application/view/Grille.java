package application.view;


import java.util.Random;
import javafx.util.Duration;

import application.model.Carte;
import application.model.Joueur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Grille{
	private Random rand = new Random();
	private Popup popup = new Popup();
	
	private int[] numbersValue;
	private int[] numbersInstall;
	private Carte[] boutonsTab;
	private Joueur[] joueurs;
	
	private Carte carteRetourne1 = null;
	private Carte carteRetourne2 = null;
	
	private Carte carteEchange1 = null;
	private Carte carteEchange2 = null;
	private int nbCarteEchange = 0;
	
	private int currentPlayerPosition;
	private Stage stageGrille;
	private Stage stageAccueil;

	// Constructeur de la classe Grille
	public Grille(Stage primaryStage ,ComboBox<Integer> comboBoxCarte, ComboBox<Integer> comboBoxJoueur, TextField[] textFields) {
		super();
		
		// Initialise le stage principal
		this.stageAccueil = primaryStage;
		
		// Initialise une boîte horizontale pour contenir les deux colonnes
		HBox hbox = new HBox();
		
		// Initialise une grille pour contenir les cartes
		GridPane gridPane = new GridPane();
		
		// Configure les marges, l'espacement vertical et horizontal de la grille
		gridPane.setPadding(new Insets(20));
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		
		// Récupère le nombre de paires de cartes sélectionné dans le comboBox de l'accueil
		int paire = Integer.valueOf(comboBoxCarte.getValue().toString());
		
		// Initialise un tableau de cartes vide
		boutonsTab = new Carte[(paire*2)];
		
		// Initialise un tableau d'entiers pour stocker les valeurs de chaque carte
		numbersValue = new int[paire];
		
		// Initialise un tableau d'entiers pour stocker les positions des cartes dans le tableau boutonsTab
		numbersInstall = new int[paire * 2];
		
		// Initialise les tableaux d'entiers avec des valeurs par défaut
		this.initTabInt(numbersValue, 30);
		this.initTabInt(numbersInstall, paire * 2);
		
		// Initialise un compteur
		int j = 1;
		
		// Boucle pour créer les paires de cartes
		for(int c = 0; c < (paire * 2); c++) {
			
			// Création de la carte 1 de la paire
			Carte carte1 = new Carte(numbersValue[paire - j], new ImageView(getClass().getResource("carte_pile.png").toExternalForm()), new ImageView(Bibliotheque.visuels.get(paire - j).getImage()));
			carte1.setMinSize(60, 80);
			carte1.setPadding(Insets.EMPTY);
			carte1.setGraphic(carte1.getImageViewCartePile());
			boutonsTab[numbersInstall[c]] = carte1;
			
			// Définition de l'action à effectuer lorsque la carte 1 est cliquée avec le bouton gauche de la souris
			carte1.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                	handleCarteClick(event, carte1);
                }
            });
			
			// Définition de l'action à effectuer lorsque la carte 1 est cliquée avec le bouton droit de la souris
			carte1.setOnMouseClicked((EventHandler<MouseEvent>) new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                	actionMouseCLick(event, carte1);
                }
            });
			
			c++;
			// Création de la carte 2 de la paire
			Carte carte2 = new Carte(numbersValue[paire - j], new ImageView(getClass().getResource("carte_pile.png").toExternalForm()), new ImageView(Bibliotheque.visuels.get(paire - j).getImage()));
			carte2.setMinSize(60, 80);
			carte2.setPadding(Insets.EMPTY);
			carte2.setGraphic(carte2.getImageViewCartePile());
			boutonsTab[numbersInstall[c]] =  carte2;
			
			// Définition de l'action à effectuer lorsque la carte 2 est cliquée avec le bouton gauche de la souris
			carte2.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                	handleCarteClick(event, carte2);
                }
            });
			
			// Définition de l'action à effectuer lorsque la carte 2 est cliquée avec le bouton droit de la souris
			carte2.setOnMouseClicked((EventHandler<MouseEvent>) new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                	actionMouseCLick(event, carte2);
                }
            });
			
			j++;
		}
		
		//Ligne 134 à 158 -> fonction qui permet de calculer le nombre de ligne et de colonne en fonction du nombre de carte séletionné par le joueur pour l'affichage des cartes
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
        btn.setText("Quitter");
        btn.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });
        
        this.joueurs = new Joueur[comboBoxJoueur.getValue()];
		
        //Initialisation des joueurs et leur affichage
        for(int i = 0;i < comboBoxJoueur.getValue();i++){
        	joueurs[i] = new Joueur(textFields[i].getText());
        	joueurs[i].setId("custom-label");
        	joueurs[i].setPrefSize(200, 50);
		}
        
        //Initialisation du joueurs courrant a 0 plus ajout d'une bordure noir
        this.currentPlayerPosition = 0;
        joueurs[currentPlayerPosition].setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        
        VBox vboxTest = new VBox();
		vboxTest.getChildren().add(gridPane);
		

		VBox vboxTest2 = new VBox();
		vboxTest2.getChildren().addAll(joueurs);
		vboxTest2.getChildren().add(btn);
		vboxTest2.setSpacing(10);
		
		hbox.getChildren().add(vboxTest);

		hbox.getChildren().add(vboxTest2);	

		Scene secondScene = new Scene(hbox, 800, 400);
		secondScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


		// New window (Stage)
		stageGrille = new Stage();
		stageGrille.setTitle("Jeu du memory");
		stageGrille.setScene(secondScene);
		// Set position of second window, related to primary window.
		stageGrille.setX(primaryStage.getX() + 200);
		stageGrille.setY(primaryStage.getY() + 100);

		stageGrille.show();
	}
	
	// La méthode initTabInt initialise un tableau d'entiers avec des valeurs aléatoires uniques, comprises entre 0 et range
	private void initTabInt(int[] numbers, int range) {
		// On utilise la classe Random pour générer des nombres aléatoires
        for (int i = 0; i < numbers.length; i++) {
        	// On génère un nombre aléatoire entre 0 et range
            int next = rand.nextInt(range);
            for (int j = 0; j < i; j++) {
            	// On vérifie si le nombre généré est déjà présent dans le tableau
                if (numbers[j] == next) {
                	// Si oui, on en génère un nouveau
                    next = rand.nextInt(range);
                    j = -1;
                    // Et on recommence la vérification depuis le début
                }
            }
            // On ajoute le nombre généré au tableau
            numbers[i] = next;
        }
	}
	
	// Cette méthode est appelée lorsqu'un utilisateur clique sur une carte
	public void handleCarteClick(ActionEvent event, Carte carte) {
		// Vérifie toutes les cartes ne sont pas retournés
		if(!ensembleCartesRertournes()) {
			// Si la première carte n'est pas retournée, retournez la carte sélectionnée et elle devient la première carte retournée
			if(carteRetourne1 == null) {
	    		carteRetourne1 = carte;
	    		carte.retourner();
	    		carte.setDisable(true);
	    		nbCarteEchange = 0;
	    	}
			// Si la deuxième carte n'est pas retournée, retournez la carte sélectionnée et elle devient la deuxième carte retournée
	    	else if(carteRetourne2 == null) {
	    		carteRetourne2 = carte;
	    		carte.retourner();
	    		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent e) -> {
	    			if(carteRetourne1.getValue() == carteRetourne2.getValue()) {
			    		joueurs[currentPlayerPosition].ajoutPoint();
			    		joueurs[currentPlayerPosition].setText(joueurs[currentPlayerPosition].getNom() + " Score : " + joueurs[currentPlayerPosition].getScore());
			    		carteRetourne1.setDisable(true);
						carteRetourne2.setDisable(true);
						carteRetourne1 = null;
						carteRetourne2 = null;
					}
	    			else {
	    				// Si plusieurs joueurs jouent, passez au joueur suivant et retournez les deux cartes 
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
			    	
	    			// Si toutes les cartes sont retournées, affichez un message de fin de partie et proposez de recommencer
			    	if(ensembleCartesRertournes()) {
						if(joueurs.length > 1) {
							int equal = 0;
							Joueur joueurScoreMaximum = joueurs[0];
							for(int i = 1; i < joueurs.length; i++) {
								if(joueurs[i].getScore() > joueurScoreMaximum.getScore()) {
									joueurScoreMaximum = joueurs[i];
									equal = 0;
								}
								else if(joueurs[i].getScore() == joueurScoreMaximum.getScore()) {
									equal++;
								}
							}
							
							if(equal > 0) {
								popup.restart("Partie terminée !", "Égalité ! "," Voulez-vous recommencer ?", "Oui", "Non", stageGrille, stageAccueil);
							}
							else {
								popup.restart("Partie terminée !", "Bien joué " + joueurScoreMaximum.getNom(), "Voulez-vous recommencer ?", "Oui", "Non", stageGrille, stageAccueil);
							}
						}
						else {
							popup.restart("Partie terminée !", "Fini !" ,"Voulez-vous recommencer ?", "Oui", "Non", stageGrille, stageAccueil);
						}
					}
	    		}));
	    		
	    		timeline.play();
	    	}
		}
    }
	
	// Vérifie si toutes les cartes ont été retournées
	public boolean ensembleCartesRertournes() {
		for(int i = 0; i < boutonsTab.length; i++) {
			if(boutonsTab[i].getGraphic() == boutonsTab[i].getImageViewCartePile()) {
				// S'il y a une carte non retournée, retourne false
				return false;
			}
		}
		// Toutes les cartes sont retournées, retourne true
		return true;
	}
	
	// Cette méthode est appelée lorsqu'un joueur clique sur une carte
	public void actionMouseCLick(MouseEvent eventHandler, Carte carte) {
		
		// On initialise le joueur ayant le score le plus faible
		Joueur joueurScoreMin = joueurs[0];
		
		// On parcourt les joueurs pour trouver celui ayant le score le plus faible
		for(Joueur joueur : joueurs) {
			if(joueurScoreMin.getScore() > joueur.getScore()) {
				joueurScoreMin = joueur;
			}
		}
		
		// Si le clic est un clic droit et que le joueur actuel est celui ayant le score le plus faible et qu'aucune carte n'est en cours d'échange
		if (eventHandler.getButton() == MouseButton.SECONDARY && joueurs[currentPlayerPosition] == joueurScoreMin && nbCarteEchange == 0) {
			// Si la première carte d'échange n'est pas encore sélectionnée
            if(carteEchange1 == null) {
            	carteEchange1 = carte;
            	carte.retourner();
            }
    		// Si la deuxième carte d'échange n'est pas encore sélectionnée
            else if(carteEchange2 == null) {
            	carteEchange2 = carte;
            	carte.retourner();
            	
            	// On définit une animation pour échanger les deux cartes sélectionnées
            	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent e) -> {
            		Carte carteTmp = new Carte(carteEchange1.getValue(), carteEchange1.getImageViewCartePile(), carteEchange1.getImageViewCarteFace());
                	
                	carteEchange1.setValue(carteEchange2.getValue());
                	carteEchange1.setImageViewCarteFace(carteEchange2.getImageViewCarteFace());
                	
                	carteEchange2.setValue(carteTmp.getValue());
                	carteEchange2.setImageViewCarteFace(carteTmp.getImageViewCarteFace());
                	
                	carteEchange1.retourner();
                	carteEchange2.retourner();
                	
                	carteEchange1 = null;
                	carteEchange2 = null;
                	
                	nbCarteEchange++;
	    		}));
	    		
	    		timeline.play();
            }
        }
	}
}
