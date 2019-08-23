package de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.PictureEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.Picture;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

/**
 * Repository-Klasse fuer die Picture-Datenbankobjekte.
 * 
 * @author Michelle Blau, Dominik Goedicke
 *
 */

public interface PictureRepository extends CrudRepository<PictureEntity, Long> {

	/**
	 * Findet Picture anhand der Picture-Id.
	 * @param pictureId - Id in der Datenbank.
	 * @return Das gefundene Picture.
	 */
	
	Picture findByPictureId(long pictureId);
	
	/**
	 * Findet Picture anhand des Picture-Namens.
	 * @param title - Name des Picture.
	 * @return Das gefundene Picture.
	 */
	
	Picture findBytitle(String title);
	
	/**
	 * Findet Picture anhand des Owners.
	 * @param owner - Ownder des Pictures.
	 * @return Das gefundene Picture.
	 */
	
	Picture findByOwner(User owner);
	
	/**
	 * Findet Picture anhand der freigebenen User.
	 * @param user - freigegebener User.
	 * @return Das gefundene Picture.
	 */
	
	List<Picture> findByRelease(User user);
	
}
