package application.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Bibliotheque {

	private final List<File> images = new ArrayList<>();
    private final List<CheckBox> checkboxes = new ArrayList<>();
    private int nombreMinimumDePaires = 30;
    
    
	public Bibliotheque(Stage primaryStage) {
		super();
		
		GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");

        Button ajouterImageButton = new Button("Ajouter une image");
        ajouterImageButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                images.add(file);
                CheckBox checkbox = new CheckBox(file.getName());
                checkboxes.add(checkbox);
                root.add(checkbox, checkboxes.size() % 5, checkboxes.size() / 5);
            }
        });

        HBox buttonsBox = new HBox(10, ajouterImageButton);
        root.add(buttonsBox, 0, 6, 5, 1);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
    
    
}
