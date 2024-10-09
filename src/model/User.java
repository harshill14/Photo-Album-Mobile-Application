package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private ArrayList<Album> albums;


	public User(String username) {

		this.username = username;
		this.albums = new ArrayList<Album>();
	}

	public String getUsername() {

		return this.username;
	}

	public ArrayList<Album> getAlbums() {
		return this.albums;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public void addAlbum(Album album) {
		// Scan through the albums
		for (int i = 0; i < albums.size(); i++) {

			// Check to see if there are any other albums with the same name
			if (albums.get(i).getAlbumName().equalsIgnoreCase(album.getAlbumName())) {

				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("There is already a photo with this name.");
				Optional<ButtonType> z = alert2.showAndWait();
				if (z.get() == ButtonType.OK) {
					alert2.close();
				}

				return;
			}
		}

		// If there are no albums of the same name, add the album
		albums.add(album);
	}

	public boolean authenticate(String username) {

		if (this.username.equals(username)) {

			return true;
		}

		return false;
	}

	public void removeAlbum(Album album) {

		if (albums.size() == 0) {

			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("No photos in album.");
			Optional<ButtonType> z = alert2.showAndWait();
			if (z.get() == ButtonType.OK) {
				alert2.close();
			}
		}

		for (int i = 0; i < albums.size(); i++) {

			if (albums.get(i).getAlbumName().equalsIgnoreCase(album.getAlbumName())) {
				// remove the photo
				albums.remove(i);

				return;
			}
		}

		Alert alert2 = new Alert(AlertType.ERROR);
		alert2.setTitle("Album not found.");
		Optional<ButtonType> result = alert2.showAndWait();
		if (result.get() == ButtonType.OK) {
			alert2.close();
		}

	}
}
