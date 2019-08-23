package de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories;

import org.springframework.data.repository.CrudRepository;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.PictureEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.Picture;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

public interface PictureRepository extends CrudRepository<PictureEntity, Long> {

	Picture findByPictureId(long pictureId);
	
	Picture findBytitle(String title);
	
	Picture findByOwner(User owner);
	
}
