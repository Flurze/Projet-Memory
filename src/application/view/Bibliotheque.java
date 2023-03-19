package application.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import application.model.Visuel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Bibliotheque {

	private Stage stageBiblio;
    private final static String IMAGES_DIRECTORY = "src/application/view/img/";
    
    public static List<Visuel> visuels = new ArrayList<>();
    public static GridPane imageGrid = new GridPane();
    public static int row = 0;
    public static int col = 0;
    
    
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
        for (File imageFile : imageFiles) {
        	addCard(imageFile);
        }
	}
    
    public void addImage(Stage stageBiblio, Stage primaryStage) {
    	
    	// Création d'un objet FileChooser pour sélectionner un fichier image
        FileChooser fileChooser = new FileChooser();
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
                addCard(destinationPath.toFile());
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
    
    public static void addCard(File imageFile) {
    	// Création de l'image et de la checkbox associée
    	Image image = new Image(imageFile.toURI().toString(), 60, 89, true, true);
        ImageView imageView = new ImageView(image);
        Visuel visuel = new Visuel(image, true);
        visuels.add(visuel);
        
        // Ajouter un écouteur d'événements sur la propriété "selected"
        visuel.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
            	visuels.add(visuel);
            }
            if(oldValue) {
            	visuels.remove(visuel);
            }
        });
        

        // Ajout de l'image et de la checkbox au conteneur
        imageGrid.add(imageView, col, row);
        imageGrid.add(visuel, col, row + 1);
        
        // Gestion de l'ajout de nouvelles images sur une nouvelle ligne
        col++;
        if (col > 15) {
            col = 0;
            row += 2;
        }
    }
}
