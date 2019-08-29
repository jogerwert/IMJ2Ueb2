package de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.UserEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories.UserRepository;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces.UserService;

/**
 * Service-Klasse, welche das User-Repository kapselt.
 * Es werden ausgewaehlte Methoden zum Laden/Speichern/Loeschen
 * von Objekten der Datenbank zur Verfuegung gestellt.
 * 
 * @author Michelle Blau, Dominik Gödicke, Johannes Gerwert
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(String userName,String userPassword,String userEmail,String dateOfBirth) {
		User user = new UserEntity(userName, userPassword, userEmail, dateOfBirth);
		return user;
	}

	@Override
	public User findByUserId(long userId) {
		return userRepository.findByUserId(userId);
	}
	
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	@Override
	public User findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}
	
	@Override
	public User findByDateOfBirth(String dateOfBirth) {
		return userRepository.findByDateOfBirth(dateOfBirth);
	}
	
	@Override
	public List<User> findAllUser() {
		List<User> userList = new ArrayList<User>();
		
		for (User user : userRepository.findAll()) {
			userList.add(user);
		}
		
		return userList;
	}
	
	/**
	 * Diese Methode speichert/ueberschreibt User-Objekte in der Datenbank.
	 * Im Falle der Speicherung wird das User-Objekt mit einer Id ausgestattet.
	 * Die Id ist der Primaerschluessel.
	 * @param user - User-Objekt, dessen userId veraendert wird, sofern es nicht in der DB existiert.
	 */
	@Override
	public void saveUser(User user) {
		userRepository.save((UserEntity)user);
	}
	
	/**
	 * Loescht ein uebergebenes User-Objekt aus der Datenbank.
	 * @param user - User-Objekt
	 */
	@Override
	public void deleteUser(User user) {
		userRepository.delete((UserEntity)user);
	}
	
	/**
	 * Ueberprueft die Login-Daten eines Benutzers.
	 * 
	 * @param userName - Eingegebener Nutzername
	 * @param userPassword - Eingegebenes Passwort
	 * @return Ergebnis der Authentifizierung
	 */
	public boolean authenticateUser(String userName, String userPassword) {
		boolean authenticated = false;
		
		User user = findByUserName(userName);
		
		if(user == null) {
			authenticated = false;
		}else if(user.getUserPassword().equals(userPassword)) {
			authenticated = true;
		}
		
		return authenticated;
	}
	
	/**
	 * Ueberprueft ob der Nutzername bereits existiert.
	 * 
	 * @param userName - Eingegebener Nutzername
	 * @return Ergebnis der Ueberpruefung
	 */
	public boolean checkUserExistance(String userName) {
		boolean userDoesNotExist = false;
		
		User user = findByUserName(userName);
		
		if(user == null) {
			if(!user.getUserName().isEmpty()) {
				userDoesNotExist = true;
			}
		}
		
		return userDoesNotExist;
	}
}
