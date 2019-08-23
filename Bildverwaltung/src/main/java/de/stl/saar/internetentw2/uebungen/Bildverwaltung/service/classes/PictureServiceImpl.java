package de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.PictureEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.Picture;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories.PictureRepository;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces.PictureService;

public class PictureServiceImpl implements PictureService{
	
	@Autowired
	private PictureRepository pictureRepository;
	
	@Override
	public Picture createPicture(String picturePath, String title, String description, User owner, List<User> releaseList) {
		Picture picture = new PictureEntity(picturePath, title, description, owner, releaseList);
		return picture;
	}
	
	@Override
	public Picture findByPictureId(long pictureId) {
		return pictureRepository.findByPictureId(pictureId);
	}
	
	@Override
	public Picture findBytitle(String title) {
		return pictureRepository.findBytitle(title);
	}
	
	@Override
	public Picture findByOwner(User owner) {
		return pictureRepository.findByOwner(owner);
	}
	
	@Override
	public List<Picture> findByRelease(User user) {
		return pictureRepository.findByRelease(user);
	}
	
	@Override
	public List<Picture> findAllPictures() {
		List<Picture> pictureList = new ArrayList<Picture>();
		
		for(Picture picture : pictureRepository.findAll()) {
			pictureList.add(picture);
		}
		
		return pictureList;
	}
	
	@Override
	public void savePicture(Picture picture) {
		pictureRepository.save((PictureEntity)picture);
	}
	
	@Override
	public void deletePicture(Picture picture) {
		pictureRepository.delete((PictureEntity)picture);
	}
	
	@Override
	public String summarizePicture(Picture picture) {
		//TODO muss gemacht werden
		return "";
	}

}
