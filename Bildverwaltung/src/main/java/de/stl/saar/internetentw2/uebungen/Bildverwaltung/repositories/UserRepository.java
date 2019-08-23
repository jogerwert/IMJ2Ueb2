package de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.UserEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	User findByUserId(long userId);
	
	User findByUserName(String userName);
	
	User findByUserEmail(String userEmail);
	
	User findByDateOfBirth(Date dateOfBirth);
}
