package controller;

/**
 * @author Dev & Harshil
 */
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

import model.Album;
import model.Photo;
import model.Tag;
import model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.SerializableSave;

public class LoginController implements Serializable {

	@FXML
	Button loginButton;

	@FXML
	Button newUserButton;

	@FXML
	TextField usernameField;

	@FXML
	PasswordField passwordField;

	@FXML
	Button exit;

	// SerializableObjects userlist = new SerializableObjects();

	Stage firstS;

	ArrayList<User> users = new ArrayList<User>();

	// changes to true if stock photos are there
	boolean stockExists = false;

	/**
	 * if exit button then stop the code
	 */
	@FXML
	void exit() {
		System.exit(0);
	}

	/**
	 * Start --> reads and writes the save file
	 * 
	 * Also checks for the stock photos
	 * 
	 * @param firstS
	 */
	public void start(Stage firstS) {

		// gets saved data and the stock files
		try {
			users = SerializableSave.read();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// closed
		firstS.setOnCloseRequest(event -> {

			try {
				SerializableSave.save(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals("stock")) {
				stockExists = true;
			}
		}
		// if data doesn't exists need to create a stock file with 5 photos
		if (!stockExists) {
			createStockUser();
		}
		this.firstS = firstS;
	}

	/**
	 * When login button is clicked... it goes inside
	 * 
	 * @param e
	 * @throws Exception
	 */
	public void login(ActionEvent e) throws Exception {

		// Put the username and password into a String to compare
		String username = usernameField.getText();

//		Parent parent;
		// userlist = SerializableObjects.read();

//		if (username.length() == 0) {
//
//			// Throw an alert, prompting the user to input a username
//			Alert usernameAlert = new Alert(AlertType.ERROR);
//			usernameAlert.setTitle("Textbox was empty");
//			usernameAlert.setContentText("Enter a valid USERNAME.");
//
//			// Show the optional OK button to push to close the alert
//			Optional<ButtonType> z = usernameAlert.showAndWait();
//
//			if (z.get() == ButtonType.OK) {
//
//				usernameAlert.close();
//			}
//		}

		/**
		 * gives alert if user input was null or size = 0
		 */
		if (users == null || users.size() == 0) {

			// Throw an alert, prompting the user to input a username
			Alert usernameAlert = new Alert(AlertType.ERROR);
			usernameAlert.setTitle("Login Error.");
			usernameAlert.setContentText("Enter a valid USERNAME.");

			// Show the optional OK button to push to close the alert
			Optional<ButtonType> result = usernameAlert.showAndWait();

			if (result.get() == ButtonType.OK) {

				usernameAlert.close();
			}
		}
		/**
		 * if input = admin then loads admin screen
		 */
		if (username.equals("admin")) {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/AdminScreen.fxml"));
			try {
				firstS.close();
				AnchorPane root = (AnchorPane) loader.load();
				AdminController adminview = loader.getController();
				Stage stage = new Stage();

				SerializableSave.save(users);
				adminview.start(stage);
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			} catch (IOException exception) {
				exception.printStackTrace();
			}

		}

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).authenticate(username)) {
				if (username.equals("admin")) {
					firstS.close();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/view/AdminScreen.fxml"));
					try {
						AnchorPane root = (AnchorPane) loader.load();
						AdminController adminview = loader.getController();
						Stage stage = new Stage();
						SerializableSave.save(users);

						adminview.start(stage);
						Scene scene = new Scene(root, 800, 600);
						stage.setScene(scene);
						stage.show();

					} catch (IOException exception) {
						exception.printStackTrace();
					}

				}
				/**
				 * after all the checks... logs into a username that was specified and opens the
				 * ALbums screen
				 */
				else {

					firstS.close();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/view/AlbumsScreen.fxml"));
					try {
						AnchorPane root = (AnchorPane) loader.load();
						AlbumController albumview = loader.getController();
						Stage stage = new Stage();
						SerializableSave.save(users);

						albumview.start(stage, i);
						Scene scene = new Scene(root, 800, 600);
						stage.setScene(scene);
						stage.show();

					} catch (IOException exception) {
						exception.printStackTrace();
					}

				}

			} else {

			}

		}
	}

	/**
	 * creates a stock user file.... gives 5 stock photos with TAGS and CAPTIONS
	 */
	public void createStockUser() {

		User user = new User("stock");

		Album stockAlbum = new Album();
		stockAlbum.setAlbumName("Stock Photos");

		Photo corvette = new Photo();
		corvette.setPhoto(new Image("car1.jpg"));
		corvette.setLocation("car1.jpg");
		corvette.setPhotoName("Corvette");
		corvette.setCaption("Shiv's Favorite Car");
		ArrayList<Tag> corvetteTags = new ArrayList<Tag>();
		corvetteTags.add(new Tag("corvette", "sexy"));
		corvetteTags.add(new Tag("corvette", "beautiful"));
		corvette.setTags(corvetteTags);
		stockAlbum.addPhoto(corvette);

		Photo p1 = new Photo();
		p1.setPhoto(new Image("p1.jpg"));
		p1.setLocation("p1.jpg");
		p1.setPhotoName("McLaren P1");
		p1.setCaption("Manav's Favorite Car");
		ArrayList<Tag> p1Tags = new ArrayList<Tag>();
		p1Tags.add(new Tag("McLaren P1", "sexy"));
		p1Tags.add(new Tag("McLaren P1", "beautiful"));
		p1.setTags(p1Tags);
		stockAlbum.addPhoto(p1);

		Photo lotus = new Photo();
		lotus.setPhoto(new Image("lotus.jpg"));
		lotus.setLocation("lotus.jpg");
		lotus.setPhotoName("Lotus Evora");
		lotus.setCaption("Manan's Favorite Car.");
		ArrayList<Tag> lotusTags = new ArrayList<Tag>();
		lotusTags.add(new Tag("Lotus", "sexy"));
		lotusTags.add(new Tag("Lotus", "beautiful"));
		lotus.setTags(lotusTags);
		stockAlbum.addPhoto(lotus);

		Photo lamborghini = new Photo();
		lamborghini.setPhoto(new Image("lamboghini.jpg"));
		lamborghini.setLocation("lamboghini.jpg");
		lamborghini.setPhotoName("Lamborghini");
		lamborghini.setCaption("Deep's Favorite Car.");
		stockAlbum.addPhoto(lamborghini);

		Photo r8 = new Photo();
		r8.setPhoto(new Image("r8.jpg"));
		r8.setLocation("r8.jpg");
		r8.setPhotoName("Audi r8");
		r8.setCaption("Dev's Favorite Car");
		stockAlbum.addPhoto(r8);

		user.addAlbum(stockAlbum);

		users.add(user);
	}
}
