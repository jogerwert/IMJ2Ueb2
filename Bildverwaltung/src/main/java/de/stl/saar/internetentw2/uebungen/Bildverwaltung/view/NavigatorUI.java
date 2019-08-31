package de.stl.saar.internetentw2.uebungen.Bildverwaltung.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.UI;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

/**
 * Vaadin UI Klasse
 * Konfiguriert das Hauptfenster und ruft die Views auf.
 * 
 * @author Johannes Gerwert
 */
@SpringUI
@SpringViewDisplay
public class NavigatorUI extends UI {
	private static final long serialVersionUID = 1729300377885965477L;
	protected static final String LOGINVIEW = "login";
	protected static final String PICTUREVIEW = "pictures";
	
	@Autowired
	private SpringNavigator navigator;
	
	private User currentUser;
	
	/**
	 * Konfiguriert das Hauptfenster und ruft die naechste View auf.
	 */
	protected void init(VaadinRequest request) {
		getPage().setTitle("Bildverwaltung");
		
		currentUser = null;
		navigator.navigateTo("login");
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
}
