package de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories;

import org.springframework.data.repository.CrudRepository;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.UserEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

/**
 * Repository-Klasse fuer die User-Datenbankobjekte.
 * 
 * @author Michelle Blau, Dominik Goedicke
 *
 */

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	/**
	 * Findet User anhand der User-Id.
	 * @param userId - Id in der Datenbank.
	 * @return Der gefundene User.
	 */
	
	User findByUserId(long userId);
	
	/**
	 * Findet User anhand des User-Namen.
	 * @param userName - Name des User.
	 * @return Der gefundene User.
	 */
	
	User findByUserName(String userName);
	
	/**
	 * Findet User anhand des User-Email.
	 * @param userEmail - Email des User.
	 * @return Der gefundene User.
	 */
	
	User findByUserEmail(String userEmail);
	
	/**
	 * Findet User anhand des Geburtsdatums.
	 * @param dateOfBirth - Geburtsdatum des User.
	 * @return Der gefundene User.
	 */
	
	User findByDateOfBirth(String dateOfBirth);
}
