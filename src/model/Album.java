package model;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Album implements Serializable {

	private static final long serialVersionUID = 1L;

	private String albumName;

	private ArrayList<Photo> photos;

	public Album() {

		this.photos = new ArrayList<Photo>();
		this.albumName = albumName;
	}

	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public ArrayList<Photo> getPhotos() {
		return this.photos;
	}

	public int getAlbumSize() {
		return photos.size();
	}

	public void renameAlbum(String newName) {
		this.albumName = newName;

	}

	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}

	public void addPhoto(Photo photo) {
		if (photos == null) {
			photos = new ArrayList<Photo>();
		}

		// Scan through the photos in the album
		for (int i = 0; i < photos.size(); i++) {

			// Check to see if there are any other photos with the same name
			if (photos.get(i).getPhotoName().equalsIgnoreCase(photo.getPhotoName())) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Name already exists");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					alert.close();
				}
				return;
			}
		}
		photos.add(photo);
	}

	public void deletePhoto(String photoName) {

		if (photos.size() == 0) {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("No photos in this album.");
			Optional<ButtonType> z = alert2.showAndWait();
			if (z.get() == ButtonType.OK) {
				alert2.close();
			}
		}

		for (int i = 0; i < photos.size(); i++) {
			if (photos.get(i).getPhotoName().equalsIgnoreCase(photoName)) {
				photos.remove(i);
				return;
			}
		}

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Photo not found.");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			alert.close();
		}
	}

//	public void sortAlbumByDate() {
//		if (this.getAlbumSize() == 0 || this.getAlbumSize() == 1) {
//			return;
//		}
//		Collections.sort(photos, new Comparator<Photo>() {
//			@Override
//			public int compare(Photo p1, Photo p2) {
//				return p1.getDate().compareTo(p2.getDate());
//			}
//		});
//	}
}
