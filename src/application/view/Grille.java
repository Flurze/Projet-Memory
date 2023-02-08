package application.view;

import java.util.HashSet;
import java.util.Random;

import application.model.Carte;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Grille {
	
	private int[] numbers;
	private int[] numbersInstall;
	private Random rand = new Random();

	public Grille(Stage primaryStage ,ComboBox comboBoxCarte, ComboBox<Integer> comboBoxJoueur, TextField[] textFields) {
		super();
		
		HBox hbox = new HBox();
		
		GridPane gridPane = new GridPane();
		
		gridPane.setPadding(new Insets(20));
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		
		int paire = Integer.valueOf(comboBoxCarte.getValue().toString());
		
		Button[] boutonsTab = new Button[(paire*2)];
		
		numbers = new int[paire];
		
		numbersInstall = new int[paire * 2];
		
		this.initTabInt(numbers, 100);
		this.initTabInt(numbersInstall, paire * 2);
		
		int j = 1;
		
		for(int c = 0; c < (paire * 2); c++) {
			Carte carte1 = new Carte(" " + numbers[paire - j]);
			carte1.setMinSize(60, 80);
			ImageView imageView1 = new ImageView(getClass().getResource("cart.png").toExternalForm());
			carte1.setGraphic(imageView1);
			boutonsTab[numbersInstall[c]] =  carte1;
			c++;
			Carte carte2 = new Carte(" " + numbers[paire - j]);
			carte2.setMinSize(60, 80);
			ImageView imageView2 = new ImageView(getClass().getResource("cart.png").toExternalForm());
			carte2.setGraphic(imageView2);
			boutonsTab[numbersInstall[c]] =  carte2;
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
}
