package application.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Bibliotheque {

	private Stage stageBiblio;
    private final static String IMAGES_DIRECTORY = "src/application/view/img/";
    
    public static List<CheckBox> checkboxs = new ArrayList<>();
    public static GridPane imageGrid = new GridPane();
    
    
	public Bibliotheque(Stage primaryStage) {
		super();
		
        imageGrid.setHgap(10);
        imageGrid.setVgap(10);
        imageGrid.setPadding(new Insets(10));
        
        Button butonAdd = new Button();
        butonAdd.setText("Ajouter une image");
        butonAdd.setOnAction((ActionEvent e) -> {
        	addImage(stageBiblio, primaryStage);
        });
        
        VBox vboxTest2 = new VBox();
		vboxTest2.getChildren().addAll(butonAdd, imageGrid);
		vboxTest2.setSpacing(10);

        // Création de la scène
        Scene scene = new Scene(vboxTest2);
        
        stageBiblio = new Stage();
		stageBiblio.setTitle("Bibliothèque");
		stageBiblio.setScene(scene);
		stageBiblio.setX(primaryStage.getX() + 200);
		stageBiblio.setY(primaryStage.getY() + 100);
		stageBiblio.show();
	}
	
	public static void AllImagesCheckboxs() {
		// Récupération de la liste des fichiers dans le dossier des images
        File directory = new File(IMAGES_DIRECTORY);
        File[] imageFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

        // Parcours de la liste des fichiers pour afficher les images
        int row = 0;
        int col = 0;
        for (File imageFile : imageFiles) {
        	
            // Création de l'image et de la checkbox associée
            ImageView imageView = new ImageView(new Image(imageFile.toURI().toString(), 60, 89, true, true));
            CheckBox checkbox = new CheckBox();
            checkboxs.add(checkbox);
            checkbox.setSelected(true);
            
            // Ajouter un écouteur d'événements sur la propriété "selected"
            checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue) {
                	checkboxs.add(checkbox);
                }
                if(oldValue) {
                	checkboxs.remove(checkbox);
                }
            });
            

            // Ajout de l'image et de la checkbox au conteneur
            imageGrid.add(imageView, col, row);
            imageGrid.add(checkbox, col, row + 1);

            // Gestion de l'ajout de nouvelles images sur une nouvelle ligne
            col++;
            if (col > 15) {
                col = 0;
                row += 2;
            }
        }
	}
    
    public void addImage(Stage stageBiblio, Stage primaryStage) {
    	
    	// Création d'un objet FileChooser pour sélectionner un fichier image
        FileChooser fileChooser = new FileChooser();
        
        // Définir un titre pour la boîte de dialogue de sélection de fichier
        fileChooser.setTitle("Sélectionner une image de 60x89 pixels en PNG");


        // Ajout des filtres pour les fichiers image
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG 60x89", "*.png"));
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PNG 60x89", "*.png"));
        
        // Affichage de la fenêtre de sélection de fichier et récupération du fichier sélectionné
        File selectedFile = fileChooser.showOpenDialog(stageBiblio);
        
        try {
            // Vérifier que le fichier est une image PNG
            if (!selectedFile.getName().toLowerCase().endsWith(".png")) {
                Popup popup = new Popup();
                popup.confirmation("Ajout fichier", "Le fichier doit être au format PNG.", "OK", stageBiblio, primaryStage);
            }
            else {
            	// Charger l'image et vérifier ses dimensions
                Image image = new Image(selectedFile.toURI().toString());
                if (image.getWidth() != 60 || image.getHeight() != 89) {
                    Popup popup2 = new Popup();
                    popup2.confirmation("Ajout fichier", "Les dimensions de l'image doivent être de 60x89 pixels.", "OK", stageBiblio, primaryStage);
                }
                else {
                	if(!this.imageExisting(selectedFile)) {
                		downloadImage(selectedFile);
                    	Bibliotheque.AllImagesCheckboxs();
                	}
                	else {
                		Popup popup3 = new Popup();
                        popup3.confirmation("Ajout fichier", "L'image existe déjà.", "OK", stageBiblio, primaryStage);
                	}
                }
            }
            
        } catch (Exception e) {
            Popup popup = new Popup();
            popup.confirmation("Ajout fichier", "Le fichier n'est pas une image valide.", "OK", stageBiblio, primaryStage);
        }
    }
    
    public void downloadImage(File selectedFile) {
    	if (selectedFile != null) {
            Path sourcePath = Paths.get(selectedFile.toURI());
            Path destinationPath = Paths.get(IMAGES_DIRECTORY + selectedFile.getName());
            try {
                Files.copy(sourcePath, destinationPath);
            } catch (IOException e) {
                // Gérer les erreurs de copie de fichier
                e.printStackTrace();
            }
        }
    }
    
    public boolean imageExisting(File file) {
    	// Récupération de la liste des fichiers dans le dossier des images
        File directory = new File(IMAGES_DIRECTORY);
        File[] imageFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        
        for (File imageFile : imageFiles) {
        	if(imageFile.getName().toLowerCase().equals(file.getName().toLowerCase())) {
        		return true;
        	}
        }
        
        return false;
    }
}
