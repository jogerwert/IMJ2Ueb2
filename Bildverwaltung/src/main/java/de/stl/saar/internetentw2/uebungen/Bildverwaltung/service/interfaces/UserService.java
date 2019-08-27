package de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces;


import java.util.List;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

/**
 * Interface zur Kapselung von Service-Objekten fuer User.
 * 
 * @author Michelle Blau, Dominik Goedicke, Johannes Gerwert
 *
 */

public interface UserService {
	
	User createUser(String userName,String userPassword,String userEmail,String dateOfBirth);

	User findByUserId(long userId);
	
	User findByUserName(String userName);
	
	User findByUserEmail(String userEmail);
	
	User findByDateOfBirth(String dateOfBirth);
	
	List<User> findAllUser();
	
	void saveUser(User user);
	
	void deleteUser(User user);
	
	boolean authenticateUser(String userName, String userPassword);
	
	boolean checkUserExistance(String userName);
}
