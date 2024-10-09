package controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.stage.Stage;
import model.*;

public class PhotoSearchController implements Serializable {

	// Album screen FXML
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
	Label usernameLabel;

	Stage firstS; // main stage

	ArrayList<Photo> reslist = new ArrayList<Photo>();
	ArrayList<Album> albums = new ArrayList<Album>();
	ArrayList<Photo> allPhotos = new ArrayList<Photo>();
	ArrayList<User> users = new ArrayList<User>();
	int reslisti = 0; // this is the index for reslist

	public void start(Stage firstS, int index) {
		this.firstS = firstS;
		try {
			users = SerializableSave.read();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		refresh();
		this.albums = users.get(index).getAlbums();
		pane00.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
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
				}
				event.consume();
			}
		});

		pane11.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// this will highlight the album and give a border, so you know its selected
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
				}
				event.consume();
			}
		});
		firstS.setOnCloseRequest(event -> {

			try {
				SerializableSave.save(users);
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

	}

	/**
	 * Searches all the users albums for photos with matching tags
	 */
	public void searchByTag() {

		String tagName = "";
		String tagValue = "";

		allPhotos();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Search By Tag");
		alert.setHeaderText("Search By Tag");
		alert.setResizable(true);
		Label label1 = new Label("Tag Name: ");
		Label label2 = new Label("Tag Value: ");

		TextField tf1 = new TextField();
		TextField tf2 = new TextField();

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf1, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);
		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			if (tf1.getText().isEmpty()) {
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("Empty");
				alert2.setContentText("Enter a valid name");
				Optional<ButtonType> result2 = alert2.showAndWait();
				if (result2.get() == ButtonType.OK) {
					alert2.close();
				}
			} else {
				tagName = tf1.getText();
				tagValue = tf2.getText();
				alert.close();
			}
		}

		reslist = new ArrayList<Photo>();

		if (albums.size() == 0) {

		} else {

			for (int i = 0; i < allPhotos.size(); i++) {

				for (int j = 0; j < allPhotos.get(i).getTags().size(); j++) {

					if (allPhotos.get(i).getTags().get(j).getTagName().equalsIgnoreCase(tagName)
							&& allPhotos.get(i).getTags().get(j).getTagValue().equalsIgnoreCase(tagValue)) {

						reslist.add(allPhotos.get(i));
					}
				}
			}
		}

		insertImages();
	}

	public void searchByDateRange() {

		int startDay = 0, endDay = 0;
		int startMonth = 0, endMonth = 0;
		int startYear = 0, endYear = 0;

		allPhotos();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().setMinHeight(AnchorPane.USE_PREF_SIZE);
		alert.setTitle("Search By Date Range");
		alert.setHeaderText("Please enter dates in number form (ex. 12 2 1994");
//		alert.setResizable(true);
		Label label1 = new Label("Start Date - Day: ");
		Label label2 = new Label("Start Date - Month: ");
		Label label3 = new Label("Start Date - Year: ");
		Label label4 = new Label("End Date - Day: ");
		Label label5 = new Label("End Date - Month: ");
		Label label6 = new Label("End Date - Year: ");

		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		TextField text3 = new TextField();
		TextField text4 = new TextField();
		TextField text5 = new TextField();
		TextField text6 = new TextField();

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf1, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);
		grid.add(label3, 1, 3);
		grid.add(text3, 2, 3);
		grid.add(label4, 1, 4);
		grid.add(text4, 2, 4);
		grid.add(label5, 1, 5);
		grid.add(text5, 2, 5);
		grid.add(label6, 1, 6);
		grid.add(text6, 2, 6);

		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> x = alert.showAndWait();

		if (x.get() == ButtonType.OK) {
			if (tf1.getText().isEmpty()) {
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("You must enter an photo name");
				Optional<ButtonType> z = alert2.showAndWait();
				if (z.get() == ButtonType.OK) {
					alert2.close();
				}
			} else {

				startDay = Integer.parseInt(tf1.getText());
				endDay = Integer.parseInt(text4.getText());

				startMonth = Integer.parseInt(tf2.getText());
				endMonth = Integer.parseInt(text5.getText());

				startYear = Integer.parseInt(text3.getText());
				endYear = Integer.parseInt(text6.getText());

				alert.close();

			}
		}

		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

		reslist = new ArrayList<Photo>();

		if (albums.size() == 0) {

		} else {

			for (int i = 0; i < allPhotos.size(); i++) {

				Date testDate = allPhotos.get(i).getDate();
				LocalDate date = testDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				if (date.isAfter(startDate) && date.isBefore(endDate)) {

					reslist.add(allPhotos.get(i));
				}
			}
		}

		insertImages();

	}

	public void newAlbumFromResult() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Add Album");
		alert.setHeaderText("Creating an Album");
		alert.setResizable(true);
		Label label1 = new Label("Album Name: ");

		TextField tf1 = new TextField();

		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf1, 2, 1);
		alert.getDialogPane().setContent(grid);

		Optional<ButtonType> x = alert.showAndWait();

		if (x.get() == ButtonType.OK) {
			if (tf1.getText().isEmpty()) {
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("You must enter an Album name");
				Optional<ButtonType> z = alert2.showAndWait();
				if (z.get() == ButtonType.OK) {
					alert2.close();
				}
			} else {
				Album album = new Album();
				album.setAlbumName(tf1.getText());

				for (int i = 0; i < reslist.size(); i++) {

					album.addPhoto(reslist.get(i));
				}

				albums.add(album);
				// *****user.deletealum*****
				alert.close();

			}
		}

		try {
			SerializableSave.save(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Moves to the next page of albums
	 */
	public void searchIndexNext() {

		if (reslist.size() - reslisti > 5) {
			reslisti += 6;
		}

		refresh();
		insertImages();
	}

	/**
	 * Returns to the previous page of albums
	 */
	public void searchIndexPrev() {

		if (reslisti - 6 >= 0) {

			reslisti -= 6;
		}

		refresh();
		insertImages();
	}

	/**
	 * Resets the screen to an empty screen
	 */
	public void refresh() {

		imageView00.setImage(null);
		imageView10.setImage(null);
		imageView01.setImage(null);
		imageView11.setImage(null);
		imageView02.setImage(null);
		imageView12.setImage(null);
		;
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

	public void insertImages() {

		if (reslist == null || reslist.size() == 0) {

		} else {

			if (reslist.size() - reslisti > 5) {

				imageView00.setImage(reslist.get(reslisti).getPhoto());
				;
				photoName00.setText(reslist.get(reslisti).getPhotoName());
				caption00.setText(reslist.get(reslisti).getCaption());

				imageView10.setImage(reslist.get(reslisti + 1).getPhoto());
				photoName10.setText(reslist.get(reslisti + 1).getPhotoName());
				caption10.setText(reslist.get(reslisti + 1).getCaption());

				imageView01.setImage(reslist.get(reslisti + 2).getPhoto());
				photoName01.setText(reslist.get(reslisti + 2).getPhotoName());
				caption01.setText(reslist.get(reslisti + 2).getCaption());

				imageView11.setImage(reslist.get(reslisti + 3).getPhoto());
				photoName11.setText(reslist.get(reslisti + 3).getPhotoName());
				caption11.setText(reslist.get(reslisti + 3).getCaption());

				imageView02.setImage(reslist.get(reslisti + 4).getPhoto());
				photoName02.setText(reslist.get(reslisti + 4).getPhotoName());
				caption02.setText(reslist.get(reslisti + 4).getCaption());

				imageView12.setImage(reslist.get(reslisti + 5).getPhoto());
				photoName12.setText(reslist.get(reslisti + 5).getPhotoName());
				caption12.setText(reslist.get(reslisti + 5).getCaption());
			} else if (reslist.size() - reslisti > 4) {

				imageView00.setImage(reslist.get(reslisti).getPhoto());
				;
				photoName00.setText(reslist.get(reslisti).getPhotoName());
				caption00.setText(reslist.get(reslisti).getCaption());

				imageView10.setImage(reslist.get(reslisti + 1).getPhoto());
				photoName10.setText(reslist.get(reslisti + 1).getPhotoName());
				caption10.setText(reslist.get(reslisti + 1).getCaption());

				imageView01.setImage(reslist.get(reslisti + 2).getPhoto());
				photoName01.setText(reslist.get(reslisti + 2).getPhotoName());
				caption01.setText(reslist.get(reslisti + 2).getCaption());

				imageView11.setImage(reslist.get(reslisti + 3).getPhoto());
				photoName11.setText(reslist.get(reslisti + 3).getPhotoName());
				caption11.setText(reslist.get(reslisti + 3).getCaption());

				imageView02.setImage(reslist.get(reslisti + 4).getPhoto());
				photoName02.setText(reslist.get(reslisti + 4).getPhotoName());
				caption02.setText(reslist.get(reslisti + 4).getCaption());
			} else if (reslist.size() - reslisti > 3) {

				imageView00.setImage(reslist.get(reslisti).getPhoto());
				;
				photoName00.setText(reslist.get(reslisti).getPhotoName());
				caption00.setText(reslist.get(reslisti).getCaption());

				imageView10.setImage(reslist.get(reslisti + 1).getPhoto());
				photoName10.setText(reslist.get(reslisti + 1).getPhotoName());
				caption10.setText(reslist.get(reslisti + 1).getCaption());

				imageView01.setImage(reslist.get(reslisti + 2).getPhoto());
				photoName01.setText(reslist.get(reslisti + 2).getPhotoName());
				caption01.setText(reslist.get(reslisti + 2).getCaption());

				imageView11.setImage(reslist.get(reslisti + 3).getPhoto());
				photoName11.setText(reslist.get(reslisti + 3).getPhotoName());
				caption11.setText(reslist.get(reslisti + 3).getCaption());
			} else if (reslist.size() - reslisti > 2) {

				imageView00.setImage(reslist.get(reslisti).getPhoto());
				;
				photoName00.setText(reslist.get(reslisti).getPhotoName());
				caption00.setText(reslist.get(reslisti).getCaption());

				imageView10.setImage(reslist.get(reslisti + 1).getPhoto());
				photoName10.setText(reslist.get(reslisti + 1).getPhotoName());
				caption10.setText(reslist.get(reslisti + 1).getCaption());

				imageView01.setImage(reslist.get(reslisti + 2).getPhoto());
				photoName01.setText(reslist.get(reslisti + 2).getPhotoName());
				caption01.setText(reslist.get(reslisti + 2).getCaption());
			} else if (reslist.size() - reslisti > 1) {

				imageView00.setImage(reslist.get(reslisti).getPhoto());
				;
				photoName00.setText(reslist.get(reslisti).getPhotoName());
				caption00.setText(reslist.get(reslisti).getCaption());

				imageView10.setImage(reslist.get(reslisti + 1).getPhoto());
				photoName10.setText(reslist.get(reslisti + 1).getPhotoName());
				caption10.setText(reslist.get(reslisti + 1).getCaption());
			} else if (reslist.size() - reslisti > 0) {

				imageView00.setImage(reslist.get(reslisti).getPhoto());
				;
				photoName00.setText(reslist.get(reslisti).getPhotoName());
				caption00.setText(reslist.get(reslisti).getCaption());
			}
		}
	}

	public void allPhotos() {

		for (int i = 0; i < albums.size(); i++) {

			if (albums.get(i).getPhotos().size() > 0) {

				for (int j = 0; j < albums.get(i).getPhotos().size(); j++) {

					allPhotos.add(albums.get(i).getPhotos().get(j));
				}
			}
		}
	}

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
