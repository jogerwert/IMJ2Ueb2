package de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.Picture;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

@Entity
public class PictureEntity implements Picture {
	
	private long pictureId;
	private String picturePath;
	private String title;
	private String description;
	private User owner;
	private List<User> release;
	
	protected PictureEntity() {};
	
	public PictureEntity(String picturePath, String title, String description, User owner) {
		this.picturePath = picturePath;
		this.title = title;
		this.description = description;
		this.owner = owner;
		this.release = new ArrayList<User>();
	};

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getPictureId() {
		return pictureId;
	}

	@Override
	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}

	@Override
	public String getPicturePath() {
		return picturePath;
	}

	@Override
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public User getOwner() {
		return owner;
	}

	@Override
	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public List<User> getRelease() {
		return release;
	}

	@Override
	public void setRelease(List<User> release, User user) {
		release.add(user);
		this.release = release;
		
	}
	
	@Override
	public String toString() {
		return "PictureEntity [pictureId=" + pictureId + ", picturePath=" + picturePath + ", title=" + title 
				+ ", description=" + description + ", owner" + owner + ", releases" + release + "]";
	}

}
