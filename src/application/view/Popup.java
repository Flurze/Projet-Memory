package application.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Popup {

	public Popup() {
		super();
	}
	
	public void restart(String title, String labelResult, String labelRestart, String button1, String button2, Stage stage, Stage stageAccueil) {

		Stage popupStage = new Stage();
		
		popupStage.setTitle(title);
		popupStage.initStyle(StageStyle.UNDECORATED);
		popupStage.setResizable(false);
		popupStage.setWidth(300);
		popupStage.setHeight(200);
		
		Label labelResultView = new Label(labelResult);		
		Label labelRestartView = new Label(labelRestart);
		Button againButton = new Button(button1);
		againButton.setOnAction((ActionEvent e) -> {
			popupStage.close();
			stage.close();
			stageAccueil.show();
		});
		Button closeButton = new Button(button2);
		closeButton.setOnAction(e -> Platform.exit());
		
		HBox buttonsBox = new HBox();
		buttonsBox.setAlignment(Pos.CENTER);
	    buttonsBox.getChildren().addAll(againButton, closeButton);
	    buttonsBox.setSpacing(10); // Espacement entre les boutons
	    buttonsBox.setPadding(new Insets(10, 0, 0, 0)); // Marge supérieure
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(labelResultView, labelRestartView, buttonsBox);
		layout.setAlignment(Pos.CENTER);
		Scene popupScene = new Scene(layout);
		popupStage.setScene(popupScene);
		popupStage.show();
	}
	
	public void confirmation(String title, String labelResult, String button, Stage stage, Stage stageAccueil) {
		Stage popupStage = new Stage();
		
		popupStage.setTitle(title);
		popupStage.initStyle(StageStyle.UNDECORATED);
		popupStage.setResizable(false);
		popupStage.setWidth(450);
		popupStage.setHeight(350);
		
		Label labelResultView = new Label(labelResult);		
		Button closeButton = new Button(button);
		closeButton.setOnAction(e -> popupStage.close());
		
		HBox buttonsBox = new HBox();
		buttonsBox.setAlignment(Pos.CENTER);
	    buttonsBox.getChildren().addAll(closeButton);
	    buttonsBox.setSpacing(10); // Espacement entre les boutons
	    buttonsBox.setPadding(new Insets(10, 0, 0, 0)); // Marge supérieure
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(labelResultView, buttonsBox);
		layout.setAlignment(Pos.CENTER);
		Scene popupScene = new Scene(layout);
		popupStage.setScene(popupScene);
		popupStage.show();
	}
}
