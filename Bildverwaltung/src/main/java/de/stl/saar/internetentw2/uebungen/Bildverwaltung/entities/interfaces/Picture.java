package de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces;

import java.util.List;

public interface Picture {
	
	long getPictureId();
	
	void setPictureId(long pictureId);
	
	String getPicturePath();
	
	void setPicturePath(String picturePath);
	
	String getTitle();
	
	void setTitle(String title);
	
	String getDescription();
	
	void setDescription(String description);
	
	User getOwner();
	
	void setOwner(User owner);
	
	List<User> getRelease();
	
	void setRelease(List<User> release);

}
