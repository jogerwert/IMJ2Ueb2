package de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces;

import java.util.List;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.Picture;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

public interface PictureService {
	
	Picture createPicture(String picturePath, String title, String description, User owner, List<User> releaseList);
	
	Picture findByPictureId(long pictureId);
	
	Picture findBytitle(String title);
	
	Picture findByOwner(User owner);
	
	List<Picture> findByRelease(User user);
	
	List<Picture> findAllPictures();
	
	void savePicture(Picture picture);
	
	void deletePicture(Picture picture);
	
	String summarizePicture(Picture picture);
}
