package controller;

/**
 * @author Dev & Harshil
 */
import model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SerializableSave;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class AdminController implements Serializable {

	private static final long serialVersionUID = 1L;

	@FXML
	ListView<User> listViewUsers;

	@FXML
	private Button addButton;

	@FXML
	private Button deleteButton;

	@FXML
	private Button editButton;

	@FXML
	private Button logoutButton;

	ArrayList<User> users = new ArrayList<User>();

	ObservableList<User> obsList;

	Stage mainStage;

	/**
	 * start method for admin controller
	 * 
	 * @param mainStage
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void start(Stage mainStage) throws IOException, ClassNotFoundException {

		users = SerializableSave.read();

		obsList = FXCollections.observableArrayList();

		for (int i = 0; i < users.size(); i++) {
			obsList.add(users.get(i));
		}

		listViewUsers.setItems(obsList);
		listViewUsers.getSelectionModel().select(0);
		listViewUsers.setCellFactory(param -> new ListCell<User>() {
			@Override
			protected void updateItem(User item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null || item.getUsername() == null) {
					setText(null);
				} else {
					setText(item.getUsername());

				}
			}
		});

		mainStage.setOnCloseRequest(event -> {
			mainStage.close();
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(getClass().getResource("/view/LoginScreen.fxml"));
//			try {
//				AnchorPane root = (AnchorPane) loader.load();
//				LoginController loginview = loader.getController();
//				Stage stage = new Stage();
//
//				SerializableSave.save(users);
//
//				loginview.start(stage);
//				Scene scene = new Scene(root);
//				stage.setScene(scene);
//				stage.show();
//
//			} catch (IOException exception) {
//				exception.printStackTrace();
//			}

		});
		this.mainStage = mainStage;

	}

	/**
	 * adds new users... checks for exisitng and if null input
	 * 
	 * @param event
	 */
	public void addUser(ActionEvent event) {
		// giving a pop window for addng new user
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Add User");
		alert.setHeaderText("Adding User");
		alert.setResizable(false);
		// creates a textfield with asking for user input
		Text label = new Text("Username: ");
		TextField text = new TextField();

		// create a new display grid
		GridPane grid = new GridPane();
		grid.add(label, 1, 1);
		grid.add(text, 2, 1);

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			if (text.getText().isEmpty()) {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setTitle("You must enter a User name");
				Optional<ButtonType> z = alert2.showAndWait();
				if (z.get() == ButtonType.OK) {
					alert2.close();
				}
			} else {
				User user = new User(text.getText());
				for (int i = 0; i < users.size(); i++) {

					if (text.getText().equalsIgnoreCase(users.get(i).getUsername())) {
						Alert alert2 = new Alert(Alert.AlertType.ERROR);
						alert2.setTitle("There is already a user with that username.");
						Optional<ButtonType> result3 = alert2.showAndWait();
						if (result3.get() == ButtonType.OK) {
							alert2.close();
						}
						return;
					}
				}
				obsList.add(user);
				users.add(user);
				alert.close();

			}
		} else {
			alert.close();
		}

		try {
			SerializableSave.save(users);
		} catch (IOException e) {

			System.out.println("Problem in Saving Users");
			e.printStackTrace();
		}
	}

	/**
	 * renames the prompted chosen user
	 * 
	 * Checks whether the new user is valid --> exists or isEmpty returns the
	 * replaced name
	 * 
	 * @param event
	 */
	public void rename(ActionEvent event) {

		User user = listViewUsers.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Rename Username");
		alert.setHeaderText("New Username: ");
		alert.setResizable(true);
		Label label = new Label("New Username: ");

		TextField text = new TextField();

		GridPane grid = new GridPane();
		grid.add(label, 1, 1);
		grid.add(text, 2, 1);
		text.setPromptText(user.getUsername());

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			if (text.getText().isEmpty()) {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setTitle("Enter a valid username.");
				Optional<ButtonType> result2 = alert2.showAndWait();
				if (result2.get() == ButtonType.OK) {
					alert2.close();
				}
			} else {
				// runs through a series checkers to see if the renamed user is exisiting
				for (int i = 0; i < users.size(); i++) {
					if (text.getText().equalsIgnoreCase(users.get(i).getUsername())) {
						Alert alert2 = new Alert(Alert.AlertType.ERROR);
						alert2.setTitle("There is already a user with that username.");
						Optional<ButtonType> result3 = alert2.showAndWait();
						if (result3.get() == ButtonType.OK) {
							alert2.close();
						}
						return;
					}
				}
				user.setUsername(text.getText());
				listViewUsers.refresh();
			}
		} else {
			alert.close();
		}

		try {
			SerializableSave.save(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error saving in add!");
			e.printStackTrace();
		}
	}

	/**
	 * Deletes the user at the given index and then updates the listview
	 * 
	 * @param event
	 */
	public void deleteUser(ActionEvent event) {

		User user = listViewUsers.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

		// int index = listView.getSelectionModel().getSelectedIndex();

		alert.setTitle("Album Delete");
		alert.setHeaderText("Are you sure?");
		alert.setResizable(false);

		Optional<ButtonType> result = alert.showAndWait();

		/**
		 * removes user from both obsList and the users list
		 */
		if (result.get() == ButtonType.OK) {
			obsList.remove(user);
			users.remove(user);
//			insertionSort(obsList);
			alert.close();
		} else {
			alert.close();
		}

		try {
			SerializableSave.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error saving in delete!");
			e1.printStackTrace();
		}
	}

	/**
	 * Logs out and returns to the main menu (login)
	 * 
	 * @param e the event that invoked the method call
	 */
	public void logout(ActionEvent event) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Are you sure?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {

			mainStage.close();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/LoginScreen.fxml"));
			try {
				AnchorPane root = (AnchorPane) loader.load();
				LoginController loginview = loader.getController();
				Stage stage = new Stage();

				SerializableSave.save(users);

				loginview.start(stage);
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			} catch (IOException exception) {
				exception.printStackTrace();
			}

		}
	}

}