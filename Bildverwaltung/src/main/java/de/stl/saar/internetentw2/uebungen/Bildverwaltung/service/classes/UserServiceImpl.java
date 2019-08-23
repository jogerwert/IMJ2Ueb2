package de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.UserEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories.UserRepository;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces.UserService;

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
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public User findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}
	
	public User findByDateOfBirth(String dateOfBirth) {
		return userRepository.findByDateOfBirth(dateOfBirth);
	}
	
	public List<User> findAllUser() {
		List<User> userList = new ArrayList<User>();
		
		for (User user : userRepository.findAll()) {
			userList.add(user);
		}
		
		return userList;
	}
	
	public void saveUser(User user) {
		userRepository.save((UserEntity)user);
	}
	
	public void deleteUser(User user) {
		userRepository.delete((UserEntity)user);
	}
	
	public String summarizeUser(User user) {
		//TODO muss gemacht werden
		return "";
	}
}
