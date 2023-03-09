package application;
	
import application.view.Accueil;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {	
	@Override
	public void start(Stage primaryStage) {
		try {
			Accueil accueil = new Accueil(primaryStage);
			Scene scene = new Scene(accueil.getVbox(),460,480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}