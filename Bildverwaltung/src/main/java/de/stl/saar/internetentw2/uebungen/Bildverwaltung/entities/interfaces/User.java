package de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces;

import java.util.Date;

public interface User {
	
	long getUserId();
	
	void setUserId(long userId);
	
	String getUserName();
	
	void setUserName(String userName);
	
	String getUserPassword();
	
	void setUserPassword(String userPassword);
	
	String getUserEmail();
	
	void setUserEmail(String userEmail);
	
	Date getDateOfBirth();
	
	void setDateOfBirth(Date dateOfBirth);

}
