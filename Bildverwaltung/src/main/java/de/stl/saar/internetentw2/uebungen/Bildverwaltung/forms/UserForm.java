package de.stl.saar.internetentw2.uebungen.Bildverwaltung.forms;

import java.time.LocalDate;

/**
 * Vereinfachte Darstellung von User-Objekten.
 * Zur Verwendung mit Forms.
 * 
 * @author Johannes Gerwert
 */
public class UserForm {

	private String userName;
	private String userPassword;
	private String userEmail;
	private LocalDate dateOfBirth;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
