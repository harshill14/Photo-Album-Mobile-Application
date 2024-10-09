package controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

public class PhotoViewController implements Serializable {

	Stage firstS;

	@FXML
	GridPane photoGrid;
	@FXML
	Pane pane00;
	@FXML
	Pane pane10;
	@FXML
	Pane pane01;
	@FXML
	Pane pane11;
	@FXML
	Pane pane02;
	@FXML
	Pane pane12;
	@FXML
	ImageView imageView00;
	@FXML
	ImageView imageView10;
	@FXML
	ImageView imageView01;
	@FXML
	ImageView imageView11;
	@FXML
	ImageView imageView02;
	@FXML
	ImageView imageView12;
	@FXML
	Label photoName00;
	@FXML
	Label photoName10;
	@FXML
	Label photoName01;
	@FXML
	Label photoName11;
	@FXML
	Label photoName02;
	@FXML
	Label photoName12;
	@FXML
	Label caption00;
	@FXML
	Label caption10;
	@FXML
	Label caption01;
	@FXML
	Label caption11;
	@FXML
	Label caption02;
	@FXML
	Label caption12;
	@FXML
	Label albumNameLabel;

	Album currentAlbum;

	User currentUser;

	ArrayList<User> users;

	int currIndex = 0;

	int userIndex;

	public void start(Stage firstS, int albumIndex, int userIndex) throws Exception {

		this.userIndex = userIndex;

		users = SerializableSave.read();
		currentAlbum = users.get(userIndex).getAlbums().get(albumIndex);

		this.firstS = firstS;
		this.currentUser = users.get(userIndex);

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/PhotosScreen.fxml"));

		AnchorPane root = (AnchorPane) loader.load();

		Scene scene = new Scene(root);
		firstS.setScene(scene);
		firstS.setTitle("Photo Library");
		firstS.setResizable(false);
		firstS.show();

		refresh();
		insertImages();

		pane00.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// if only one cluck
				if (event.getClickCount() == 1) {

					pane00.setBorder(new Border(new BorderStroke(Color.DARKCYAN, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(10))));

					pane10.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane01.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane11.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane02.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane12.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));
				} else if (event.getClickCount() == 2) {

					if (currIndex < currentAlbum.getPhotos().size()) {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));

						try {
							AnchorPane root = (AnchorPane) loader.load();
							PhotoOpenController openPhoto = loader.getController();
							Stage stage = new Stage();

							openPhoto.start(stage, currentAlbum, currIndex);
							Scene scene = new Scene(root, 800, 600);
							stage.setScene(scene);
							stage.show();

						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				}

				event.consume();
			}
		});

		pane10.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 1) {

					pane00.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane10.setBorder(new Border(new BorderStroke(Color.DARKCYAN, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(10))));

					pane01.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane11.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane02.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane12.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

				} else if (event.getClickCount() == 2) {

					if (currIndex + 1 < currentAlbum.getPhotos().size()) {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));

						try {
							AnchorPane root = (AnchorPane) loader.load();
							PhotoOpenController openPhoto = loader.getController();
							Stage stage = new Stage();

							openPhoto.start(stage, currentAlbum, currIndex + 1);
							Scene scene = new Scene(root, 600, 400);
							stage.setScene(scene);
							stage.show();

						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				}

				event.consume();
			}
		});

		pane01.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 1) {
					pane00.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane10.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane01.setBorder(new Border(new BorderStroke(Color.DARKCYAN, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(10))));

					pane11.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane02.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane12.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));
				} else if (event.getClickCount() == 2) {
					if (currIndex + 2 < currentAlbum.getPhotos().size()) {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));

						try {
							AnchorPane root = (AnchorPane) loader.load();
							PhotoOpenController openPhoto = loader.getController();
							Stage stage = new Stage();

							openPhoto.start(stage, currentAlbum, currIndex + 2);
							Scene scene = new Scene(root, 800, 600);
							stage.setScene(scene);
							stage.show();

						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				}

				event.consume();
			}
		});

		pane11.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 1) {

					pane00.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane10.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane01.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane11.setBorder(new Border(new BorderStroke(Color.DARKCYAN, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(10))));

					pane02.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane12.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));
				} else if (event.getClickCount() == 2) {

					if (currIndex + 3 < currentAlbum.getPhotos().size()) {

						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));

						try {
							AnchorPane root = (AnchorPane) loader.load();
							PhotoOpenController openPhoto = loader.getController();
							Stage stage = new Stage();

							openPhoto.start(stage, currentAlbum, currIndex + 3);
							Scene scene = new Scene(root, 800, 600);
							stage.setScene(scene);
							stage.show();

						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				}

				event.consume();
			}
		});

		pane02.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 1) {

					pane00.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane10.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane01.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane11.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane02.setBorder(new Border(new BorderStroke(Color.DARKCYAN, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(10))));

					pane12.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));
				} else if (event.getClickCount() == 2) {

					if (currIndex + 4 < currentAlbum.getPhotos().size()) {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));

						try {
							AnchorPane root = (AnchorPane) loader.load();
							PhotoOpenController openPhoto = loader.getController();
							Stage stage = new Stage();

							openPhoto.start(stage, currentAlbum, currIndex + 4);
							Scene scene = new Scene(root, 800, 600);
							stage.setScene(scene);
							stage.show();

						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				}

				event.consume();
			}
		});

		pane12.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 1) {

					pane00.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane10.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane01.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane11.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane02.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(1))));

					pane12.setBorder(new Border(new BorderStroke(Color.DARKCYAN, BorderStrokeStyle.SOLID,
							CornerRadii.EMPTY, new BorderWidths(10))));
				} else if (event.getClickCount() == 2) {

					if (currIndex + 5 < currentAlbum.getPhotos().size()) {

						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/view/PhotoView.fxml"));

						try {
							AnchorPane root = (AnchorPane) loader.load();
							PhotoOpenController openPhoto = loader.getController();
							Stage stage = new Stage();

							openPhoto.start(stage, currentAlbum, currIndex + 5);
							Scene scene = new Scene(root, 800, 600);
							stage.setScene(scene);
							stage.show();

						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				}

				event.consume();

			}
		});

		// when force closed
		firstS.setOnCloseRequest(event -> {

			try {
				SerializableSave.save(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	/**
	 * Transitions between two screens
	 */
	public void screenTransition() {

		firstS.close();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AlbumsScreen.fxml"));

		try {
			AnchorPane root = (AnchorPane) loader.load();
			AlbumController albumview = loader.getController();
			Stage stage = new Stage();

			albumview.start(stage, userIndex);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * Moves to the next page of photos
	 */
	public void nextPicture() {
		if (currentAlbum.getPhotos().size() - currIndex > 5) {
			currIndex += 6;
		}
		refresh();
		insertImages();
	}

	/**
	 * Moves to the previous page of photos
	 */
	public void prevPicture() {
		if (currIndex - 6 >= 0) {
			currIndex -= 6;
		}
		refresh();
		insertImages();
	}

	/**
	 * Resets the screen to a blank screen
	 */
	public void refresh() {

		imageView00.setImage(null);
		imageView10.setImage(null);
		imageView01.setImage(null);
		imageView11.setImage(null);
		imageView02.setImage(null);
		imageView12.setImage(null);

		photoName00.setText("");
		photoName10.setText("");
		photoName01.setText("");
		photoName11.setText("");
		photoName02.setText("");
		photoName12.setText("");
		caption00.setText("");
		caption10.setText("");
		caption01.setText("");
		caption11.setText("");
		caption02.setText("");
		caption12.setText("");
	}

	/**
	 * inserts images to the album
	 */
	public void insertImages() {
		albumNameLabel.setText(currentAlbum.getAlbumName());
		if (currentAlbum.getPhotos() == null || currentAlbum.getPhotos().size() == 0) {
		} else {
			if (currentAlbum.getPhotos().size() - currIndex > 5) {
				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currIndex).getCaption());

				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currIndex + 1).getCaption());

				imageView01.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 2).getLocation()));
				photoName01.setText(currentAlbum.getPhotos().get(currIndex + 2).getPhotoName());
				caption01.setText(currentAlbum.getPhotos().get(currIndex + 2).getCaption());

				imageView11.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 3).getLocation()));
				photoName11.setText(currentAlbum.getPhotos().get(currIndex + 3).getPhotoName());
				caption11.setText(currentAlbum.getPhotos().get(currIndex + 3).getCaption());

				imageView02.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 4).getLocation()));
				photoName02.setText(currentAlbum.getPhotos().get(currIndex + 4).getPhotoName());
				caption02.setText(currentAlbum.getPhotos().get(currIndex + 4).getCaption());

				imageView12.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 5).getLocation()));
				photoName12.setText(currentAlbum.getPhotos().get(currIndex + 5).getPhotoName());
				caption12.setText(currentAlbum.getPhotos().get(currIndex + 5).getCaption());
			} else if (currentAlbum.getPhotos().size() - currIndex > 4) {

				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currIndex).getCaption());

				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currIndex + 1).getCaption());

				imageView01.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 2).getLocation()));
				photoName01.setText(currentAlbum.getPhotos().get(currIndex + 2).getPhotoName());
				caption01.setText(currentAlbum.getPhotos().get(currIndex + 2).getCaption());

				imageView11.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 3).getLocation()));
				photoName11.setText(currentAlbum.getPhotos().get(currIndex + 3).getPhotoName());
				caption11.setText(currentAlbum.getPhotos().get(currIndex + 3).getCaption());

				imageView02.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 4).getLocation()));
				photoName02.setText(currentAlbum.getPhotos().get(currIndex + 4).getPhotoName());
				caption02.setText(currentAlbum.getPhotos().get(currIndex + 4).getCaption());

			} else if (currentAlbum.getPhotos().size() - currIndex > 3) {

				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currIndex).getCaption());

				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currIndex + 1).getCaption());

				imageView01.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 2).getLocation()));
				photoName01.setText(currentAlbum.getPhotos().get(currIndex + 2).getPhotoName());
				caption01.setText(currentAlbum.getPhotos().get(currIndex + 2).getCaption());

				imageView11.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 3).getLocation()));
				photoName11.setText(currentAlbum.getPhotos().get(currIndex + 3).getPhotoName());
				caption11.setText(currentAlbum.getPhotos().get(currIndex + 3).getCaption());

			} else if (currentAlbum.getPhotos().size() - currIndex > 2) {

				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currIndex).getCaption());

				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currIndex + 1).getCaption());

				imageView01.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 2).getLocation()));
				photoName01.setText(currentAlbum.getPhotos().get(currIndex + 2).getPhotoName());
				caption01.setText(currentAlbum.getPhotos().get(currIndex + 2).getCaption());

			} else if (currentAlbum.getPhotos().size() - currIndex > 1) {

				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currIndex).getCaption());

				imageView10.setImage(new Image(currentAlbum.getPhotos().get(currIndex + 1).getLocation()));
				photoName10.setText(currentAlbum.getPhotos().get(currIndex + 1).getPhotoName());
				caption10.setText(currentAlbum.getPhotos().get(currIndex + 1).getCaption());

			} else if (currentAlbum.getPhotos().size() - currIndex > 0) {

				imageView00.setImage(new Image(currentAlbum.getPhotos().get(currIndex).getLocation()));
				photoName00.setText(currentAlbum.getPhotos().get(currIndex).getPhotoName());
				caption00.setText(currentAlbum.getPhotos().get(currIndex).getCaption());
			}
		}
	}

	/**
	 * adds a new photo from your files
	 * 
	 * @param event
	 */
	public void addPhotoToAlbum(ActionEvent event) {

		final FileChooser fileChooser = new FileChooser();

		File file = fileChooser.showOpenDialog(firstS);

		if (file != null) {

			Image image1 = new Image(file.toURI().toString());

			Photo newPhoto = new Photo();
			newPhoto.setPhoto(image1);
			newPhoto.setLocation(file.toURI().toString());

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Add Photo");
			alert.setHeaderText("Adding a photo");
			alert.setResizable(true);
			Label label1 = new Label("Photo Name: ");

			TextField tf = new TextField();

			GridPane grid = new GridPane();
			grid.add(label1, 1, 1);
			grid.add(tf, 2, 1);
			alert.getDialogPane().setContent(grid);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				if (tf.getText().isEmpty()) {
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("You must enter an photo name");
					Optional<ButtonType> z = alert2.showAndWait();
					if (z.get() == ButtonType.OK) {
						alert2.close();
					}
				} else {
					newPhoto.setPhotoName(tf.getText());

					alert.close();

				}
			}
			currentAlbum.addPhoto(newPhoto);
			refresh();
			insertImages();

		}

		try {
			SerializableSave.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Deletes the photo from the album and given the location of the gridpane
	 * 
	 * @param
	 */
	public void removePhotoFromAlbum(ActionEvent e) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		int removeIndex = -1;

		if (pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
			removeIndex = currIndex;
		} else if (pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
			removeIndex = currIndex + 1;
		} else if (pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
			removeIndex = currIndex + 2;
		} else if (pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
			removeIndex = currIndex + 3;
		} else if (pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
			removeIndex = currIndex + 4;
		} else if (pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {
			removeIndex = currIndex + 5;
		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("You must specify a photo.");
			Optional<ButtonType> bt = alert2.showAndWait();
			if (bt.get() == ButtonType.OK) {
				alert2.close();
			}
		}

		alert.setTitle("Photo Deletion");
		alert.setHeaderText("Are you sure?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {

			currentAlbum.getPhotos().remove(removeIndex);
		} else {

			alert.close();
		}

		refresh();
		insertImages();

		try {
			SerializableSave.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Renames the photo within an album
	 * 
	 * @param
	 */
	public void editName(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;

		if (pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex);
		} else if (pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 1);
		} else if (pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 2);
		} else if (pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 3);
		} else if (pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 4);
		} else if (pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 5);
		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Which Photo?");
			Optional<ButtonType> result2 = alert2.showAndWait();
			if (result2.get() == ButtonType.OK) {
				alert2.close();
			}

		}

		alert.setTitle("Insert a new name.");
		alert.setHeaderText("Type a valid name");

		Label label1 = new Label("Photo Name: ");

		TextField tf = new TextField();
		tf.setPromptText(photo.getPhotoName());

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf, 2, 1);

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			// runs a checker on whether that photo name already exists
			// same thing as adding users or adding photos
			for (int i = 0; i < currentAlbum.getPhotos().size(); i++) {
				if (tf.getText().equals(currentAlbum.getPhotos().get(i).getPhotoName())) {
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setContentText("Photo Name Exists. Type another name");

					Optional<ButtonType> result2 = alert2.showAndWait();
					if (result2.get() == ButtonType.OK) {
						alert2.close();
						return;
					}
				}
			}
			photo.setPhotoName(tf.getText());

		}

		refresh();
		insertImages();

		try {
			SerializableSave.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * renames the caption of a photo
	 * 
	 * @param
	 */
	public void editCaption(ActionEvent e) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;

		if (pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex);
		} else if (pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 1);
		} else if (pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 2);
		} else if (pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 3);
		} else if (pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 4);
		} else if (pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 5);
		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Must Choose a Photo.");
			Optional<ButtonType> bt = alert2.showAndWait();
			if (bt.get() == ButtonType.OK) {
				alert2.close();
			}

		}

		alert.setTitle("New Photo Caption");
		alert.setHeaderText("Write a new caption");

		Label label1 = new Label("Photo Name");

		TextField tf = new TextField();
		tf.setPromptText(photo.getCaption());

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf, 2, 1);

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> bt = alert.showAndWait();

		if (bt.get() == ButtonType.OK) {
			photo.setCaption(tf.getText());
		} else {
			System.out.println("error in renaming caption");
			// above is for debug purposes
		}
		refresh();
		insertImages();

		try {
			SerializableSave.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * creates a tag for the photo
	 * 
	 * @param e
	 */
	public void addTag(ActionEvent e) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;

		if (pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex);
		} else if (pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 1);
		} else if (pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 2);
		} else if (pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 3);
		} else if (pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 4);
		} else if (pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 5);
		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Choose a photo.");
			Optional<ButtonType> bt = alert2.showAndWait();
			if (bt.get() == ButtonType.OK) {
				alert2.close();
			}

		}

		alert.setTitle("New Photo Tag");
		alert.setHeaderText("Write a new tag");
		alert.setResizable(true);
		Label label1 = new Label("Tag Name: ");
		Label label2 = new Label("Tag Value: ");

		TextField tf = new TextField();
		TextField tf2 = new TextField();

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> x = alert.showAndWait();

		if (x.get() == ButtonType.OK) {

			for (int i = 0; i < photo.getTags().size(); i++) {

				if (photo.getTags().get(i).getTagName().equals(tf.getText())
						&& photo.getTags().get(i).getTagValue().equals(tf2.getText())) {

					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setContentText("There is already a tag with that name and value.");

					Optional<ButtonType> y = alert2.showAndWait();

					if (y.get() == ButtonType.OK) {
						alert2.close();
						return;
					}
				}
			}

			photo.addTag(tf.getText(), tf2.getText());
		} else {

		}

		refresh();
		insertImages();

		try {
			SerializableSave.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Removes a tag from the tags list of a photo
	 * 
	 * @param e the action that triggered the method call
	 */
	public void removeTag(ActionEvent e) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;

		if (pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex);
		} else if (pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 1);
		} else if (pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 2);
		} else if (pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 3);
		} else if (pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 4);
		} else if (pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 5);
		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("You must specify a photo.");
			Optional<ButtonType> z = alert2.showAndWait();
			if (z.get() == ButtonType.OK) {
				alert2.close();
			}
		}

		alert.setTitle("Remove Photo Tag");
		alert.setHeaderText("Remove a photo tag");
		alert.setResizable(true);
		Label label1 = new Label("Tag Name: ");
		Label label2 = new Label("Tag Value: ");

		TextField tf = new TextField();
		TextField tf2 = new TextField();

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> x = alert.showAndWait();

		if (x.get() == ButtonType.OK) {

			for (int i = 0; i < photo.getTags().size(); i++) {

				if (photo.getTags().get(i).getTagName().equals(tf.getText())
						&& photo.getTags().get(i).getTagValue().equals(tf2.getText())) {

					photo.removeTag(new Tag(tf.getText(), tf2.getText()));
					return;
				}
			}

			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setContentText("There is no tag with that name and value.");

			Optional<ButtonType> y = alert2.showAndWait();

			if (y.get() == ButtonType.OK) {
				alert2.close();
				return;
			}
		} else {

		}

		refresh();
		insertImages();

		try {
			SerializableSave.save(users);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Copies the photo to a new album
	 */
	public void copyPhoto() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;
		Album album = null;

		if (pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex);
		} else if (pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 1);
		} else if (pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 2);
		} else if (pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 3);
		} else if (pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 4);
		} else if (pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 5);
		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("You must specify a photo.");
			Optional<ButtonType> z = alert2.showAndWait();
			if (z.get() == ButtonType.OK) {
				alert2.close();
			}
		}

		alert.setTitle("Copy Photo");
		alert.setHeaderText("Choose an album to copy into");
		alert.setResizable(true);
		Label label1 = new Label("Album Name: ");

		TextField tf = new TextField();

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf, 2, 1);

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> bt = alert.showAndWait();

		if (bt.get() == ButtonType.OK) {
			String temp = tf.getText();
			for (int i = 0; i < currentUser.getAlbums().size(); i++) {
				if (currentUser.getAlbums().get(i).getAlbumName().equalsIgnoreCase(temp)) {
					album = currentUser.getAlbums().get(i);
				}
			}

			if (album == null) {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setTitle("You must enter a valid Album name");
				Optional<ButtonType> z = alert2.showAndWait();
				if (z.get() == ButtonType.OK) {
					alert2.close();
				}
			} else {

				Photo newPhoto = null;
				// check to see if there's already a photo with it's name
				for (int i = 0; i < album.getPhotos().size(); i++) {

					if (album.getPhotos().get(i).getPhotoName().equalsIgnoreCase(photo.getPhotoName())) {
						newPhoto = new Photo();
						newPhoto.setPhotoName(photo.getPhotoName() + "0");
						newPhoto.setCaption(photo.getCaption());
						newPhoto.setPhoto(photo.getPhoto());
						newPhoto.setTags(photo.getTags());
						album.addPhoto(newPhoto);
					}
				}
				if (newPhoto == null) {
					album.addPhoto(photo);
				}
			}
		}
		refresh();
		insertImages();
		try {
			SerializableSave.save(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Moves the photo from the current album to a new album
	 */
	public void movePhoto() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		Photo photo = null;
		Album album = null;

		if (pane00.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex);
		} else if (pane10.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 1);
		} else if (pane01.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 2);
		} else if (pane11.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 3);
		} else if (pane02.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 4);
		} else if (pane12.getBorder().getStrokes().get(0).getWidths().getTop() == 10.0) {

			photo = currentAlbum.getPhotos().get(currIndex + 5);
		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("You must specify a photo.");
			Optional<ButtonType> bt = alert2.showAndWait();
			if (bt.get() == ButtonType.OK) {
				alert2.close();
			}
		}

		alert.setTitle("Move Photo");
		alert.setHeaderText("Move photo to which album?");
		alert.setResizable(true);
		Label label1 = new Label("Album Name: ");

		TextField tf = new TextField();

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf, 2, 1);

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> bt = alert.showAndWait();

		if (bt.get() == ButtonType.OK) {
			String temp = tf.getText();
			for (int i = 0; i < currentUser.getAlbums().size(); i++) {
				if (currentUser.getAlbums().get(i).getAlbumName().equalsIgnoreCase(temp)
						&& !(currentUser.getAlbums().get(i).getAlbumName().equals(currentAlbum.getAlbumName()))) {
					album = currentUser.getAlbums().get(i);
				}
			}
			if (album == null) {
				Alert alert2 = new Alert(Alert.AlertType.ERROR);
				alert2.setContentText("Enter a valid name.");
				Optional<ButtonType> bt1 = alert2.showAndWait();
				if (bt1.get() == ButtonType.OK) {
					alert2.close();
				}
			} else {
				Photo newPhoto = null;
				// check to see if there's already a photo with it's name
				for (int i = 0; i < album.getPhotos().size(); i++) {
					if (album.getPhotos().get(i).getPhotoName().equalsIgnoreCase(photo.getPhotoName())) {
						newPhoto = new Photo();
						newPhoto.setPhotoName(photo.getPhotoName() + "0");
						newPhoto.setCaption(photo.getCaption());
						newPhoto.setPhoto(photo.getPhoto());
						newPhoto.setTags(photo.getTags());
						album.addPhoto(newPhoto);
						currentAlbum.deletePhoto(photo.getPhotoName());
					}
				}
				if (newPhoto == null) {
					album.addPhoto(photo);
					currentAlbum.deletePhoto(photo.getPhotoName());
				}
			}
		}
		refresh();
		insertImages();
		try {
			SerializableSave.save(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Logs the user out
	 * 
	 * @param e the event that triggers the method
	 */
	public void exit(ActionEvent e) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm Logout");
		alert.setHeaderText("Are you sure?");

		Optional<ButtonType> b1 = alert.showAndWait();

		if (b1.get() == ButtonType.OK) {

			firstS.close();

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
