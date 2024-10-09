package app;
/**
 * @author Dev & Harshil
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import controller.LoginController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Photos extends Application {

	/**
	 * Start code
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/LoginScreen.fxml"));

		Parent root = (Parent) loader.load();

		LoginController login = loader.getController();
		login.start(primaryStage);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photo Library - Dev Patel & Harshil Patel");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * main for starting code
	 * @param args
	 */
	public static void main(String[] args) {
//		
//		Path filep = Paths.get("users.ser");
//		if (Files.exists(filep) == false) {
//			
//		}
		launch(args);
	}
}
