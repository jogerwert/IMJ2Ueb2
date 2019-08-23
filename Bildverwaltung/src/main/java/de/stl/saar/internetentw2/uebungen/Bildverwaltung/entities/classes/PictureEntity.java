package de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.Picture;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;

/**
 * Entity-Klasse fuer Pictures. Wird von Hibernate verwendet.
 * @author Michelle Blau, Dominik Goedicke
 *
 */

@Entity
public class PictureEntity implements Picture {
	
	private long pictureId;
	private String picturePath;
	private String title;
	private String description;
	private User owner;
	private List<User> releaseList;
	
	protected PictureEntity() {};
	
	public PictureEntity(String picturePath, String title, String description, User owner, List<User> releaseList) {
		this.picturePath = picturePath;
		this.title = title;
		this.description = description;
		this.owner = owner;
		this.releaseList = releaseList;
	};

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getPictureId() {
		return pictureId;
	}

	@Override
	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}

	@Override
	public String getPicturePath() {
		return picturePath;
	}

	@Override
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	@ManyToOne(targetEntity=UserEntity.class, cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId")
	public User getOwner() {
		return owner;
	}

	@Override
	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	@ManyToMany(targetEntity=UserEntity.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	@Fetch(value = FetchMode.SUBSELECT)
	public List<User> getRelease() {
		return releaseList;
	}

	@Override
	public void setRelease(List<User> releaseList) {
		this.releaseList=releaseList;
	}
	
	@Override
	public String toString() {
		return "PictureEntity [pictureId=" + pictureId + ", picturePath=" + picturePath + ", title=" + title 
				+ ", description=" + description + ", owner" + owner + ", releases" + releaseList + "]";
	}

}
