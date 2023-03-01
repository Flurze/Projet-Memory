package application.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Popup {

	public Popup() {
		super();
		
		Stage popupStage = new Stage();
		
		popupStage.setTitle("Ma popup");
		popupStage.initStyle(StageStyle.UNDECORATED);
		popupStage.setResizable(false);
		popupStage.setWidth(300);
		popupStage.setHeight(200);
		
		Label label = new Label("Message d'alerte !");
		Button closeButton = new Button("Fermer");
		closeButton.setOnAction(e -> popupStage.close());
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene popupScene = new Scene(layout);
		popupStage.setScene(popupScene);
		popupStage.show();
	}
}
