package de.stl.saar.internetentw2.uebungen.Bildverwaltung.forms;

/**
 * Vereinfachte Darstellung von Picture-Objekten.
 * Zur Verwendung mit Forms.
 * 
 * @author Johannes Gerwert
 */
public class PictureForm {

	private String title;
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
