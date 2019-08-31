package de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

/**
 * Entity-Klasse fuer User. Wird von Hibernate verwendet.
 * @author Michelle Blau, Dominik Goedicke
 *
 */

@Entity
public class UserEntity implements User {
	
	private long userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private Date dateOfBirth;
	
	protected UserEntity() {}
	
	public UserEntity(String userName,String userPassword,String userEmail,String dateOfBirth) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			this.dateOfBirth = format.parse(dateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getUserId() {
		return userId;
	}
	
	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
		
	}

	@Override
	public String getUserPassword() {
		return userPassword;
	}

	@Override
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		
	}

	@Override
	public String getUserEmail() {
		return userEmail;
	}

	@Override
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
		
	}

	@Override
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth=dateOfBirth;
	}
	
	@Override
	public String toString() {
		return userName;
	}
}
