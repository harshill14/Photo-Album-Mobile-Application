package model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.image.Image;

public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient Image photo;

	private String location;

	private String photoName;

	private String caption;

	private String fileType;

	private ArrayList<Tag> tags;

	private ArrayList<Album> albums;

	private Date date;

	public Photo() {
		this.date = Calendar.getInstance().getTime();
		photo = null;
		this.tags = new ArrayList<Tag>();
	}

	public Photo(Image photo) {
		this.photo = photo;
		this.tags = new ArrayList<Tag>();
	}

	public Image getPhoto() {
		return this.photo;
	}

	public void setPhoto(Image image) {
		this.photo = image;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public ArrayList<Tag> getTags() {
		return this.tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	public ArrayList<Album> getAlbums() {

		return this.albums;
	}

	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}

	public Date getDate() {
		return this.date;
	}

//	public void addAlbum() {
//
//		// TODO: Implement add album
//	}

	public void addTag(String name, String value) {
		if (name == null || value == null) {
			return;
		}
		for (int i = 0; i < tags.size(); i++) {
			if (tags.get(i).getTagName().equalsIgnoreCase(name.toLowerCase())
					&& tags.get(i).getTagValue().equalsIgnoreCase(value.toLowerCase())) {
				return;
			}
		}
		tags.add(new Tag(name, value));
		return;
	}

	public void removeTag(Tag tag) {
		if (tag.getTagName() == null || tag.getTagValue() == null) {
			return;
		}
		for (int i = 0; i < tags.size(); i++) {
			if (tags.get(i).getTagName().equalsIgnoreCase(tag.getTagName().toLowerCase())
					&& tags.get(i).getTagValue().equalsIgnoreCase(tag.getTagValue().toLowerCase())) {
				tags.remove(i);
				return;

			}
		}
	}

//	public void removeAlbum() {
//
//		// TODO: Implement remove album
//	}

}
