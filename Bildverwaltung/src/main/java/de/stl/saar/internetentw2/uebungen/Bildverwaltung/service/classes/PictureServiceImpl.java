package de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.PictureEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.Picture;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories.PictureRepository;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces.PictureService;

/**
 * Service-Klasse, welche das Picture-Repository kapselt.
 * Es werden ausgewaehlte Methoden zum Laden/Speichern/Loeschen
 * von Objekten der Datenbank zur Verfuegung gestellt.
 * 
 * @author Michelle Blau, Dominik Gödicke, Johannes Gerwert
 *
 */

@Service
public class PictureServiceImpl implements PictureService{
	
	@Autowired
	private PictureRepository pictureRepository;
	
	@Override
	public Picture createPicture(String picturePath, String title, String description, User owner, List<User> releaseList) {
		Picture picture = new PictureEntity(picturePath, title, description, owner, releaseList);
		return picture;
	}
	
	@Override
	public Picture findByPictureId(long pictureId) {
		return pictureRepository.findByPictureId(pictureId);
	}
	
	@Override
	public Picture findBytitle(String title) {
		return pictureRepository.findBytitle(title);
	}
	
	@Override
	public Picture findByOwner(User owner) {
		return pictureRepository.findByOwner(owner);
	}
	
	@Override
	public List<Picture> findByRelease(User user) {
		return pictureRepository.findByRelease(user);
	}
	
	@Override
	public List<Picture> findAllPictures() {
		List<Picture> pictureList = new ArrayList<Picture>();
		
		for(Picture picture : pictureRepository.findAll()) {
			pictureList.add(picture);
		}
		
		return pictureList;
	}
	
	/**
	 * Diese Methode speichert/ueberschreibt Picture-Objekte in der Datenbank.
	 * Im Falle der Speicherung wird das Picture-Objekt mit einer Id ausgestattet.
	 * Die Id ist der Primaerschluessel.
	 * @param picture - Picture-Objekt, dessen pictureId veraendert wird, sofern es nicht in der DB existiert.
	 */
	@Override
	public void savePicture(Picture picture) {
		pictureRepository.save((PictureEntity)picture);
	}
	
	/**
	 * Loescht ein uebergebenes Picture-Objekt aus der Datenbank.
	 * @param picture - Picture-Objekt
	 */
	@Override
	public void deletePicture(Picture picture) {
		pictureRepository.delete((PictureEntity)picture);
	}

	/**
	 * Ueberprueft ob der Titel bereits verwendet wird.
	 * 
	 * @param title - Eingegebener Titel
	 * @return Ergebnis der Ueberpruefung
	 */
	@Override
	public boolean checkTitleExistance(String title) {
		boolean titleDoesNotExist = false;
		
		Picture picture = findBytitle(title);
		
		if(picture == null) {
			if(!picture.getTitle().isEmpty()) {
				titleDoesNotExist = true;
			}
		}
		return titleDoesNotExist;
	}

	/**
	 * Ueberprueft ob das Bild bereits existiert.
	 * 
	 * @param path - Uebergebener Pfad
	 * @return Ergebnis der Ueberpruefung
	 */
	@Override
	public boolean checkPictureExistance(String path) {
		//TODO
		boolean pictureDoesNotExist = false;
		
		return false;
	}

}
