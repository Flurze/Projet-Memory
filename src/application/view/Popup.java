package application.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Popup {

	public Popup(String title, String labelMessage, String button1, String button2, Stage stage) {
		super();
		
		Stage popupStage = new Stage();
		
		popupStage.setTitle(title);
		popupStage.initStyle(StageStyle.UNDECORATED);
		popupStage.setResizable(false);
		popupStage.setWidth(300);
		popupStage.setHeight(200);
		
		Label label = new Label(labelMessage);
		Button againButton = new Button(button1);
		againButton.setOnAction((ActionEvent e) -> {
			popupStage.close();
			stage.close();
		});
		Button closeButton = new Button(button2);
		closeButton.setOnAction(e -> Platform.exit());
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton, againButton);
		layout.setAlignment(Pos.CENTER);
		Scene popupScene = new Scene(layout);
		popupStage.setScene(popupScene);
		popupStage.show();
	}
}
