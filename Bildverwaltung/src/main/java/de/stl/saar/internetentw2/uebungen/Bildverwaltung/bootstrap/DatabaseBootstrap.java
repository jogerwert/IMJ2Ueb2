package de.stl.saar.internetentw2.uebungen.Bildverwaltung.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.PictureEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.classes.UserEntity;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories.PictureRepository;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.repositories.UserRepository;

@Component
public class DatabaseBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PictureRepository pictureRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initializeDatabase();	
	}
	
	public void initializeDatabase() {
		userRepository.save(new UserEntity("aldumbledore","12345678","aldumbledore@htwsaar.de","12.12.1950"));
		userRepository.save(new UserEntity("sesnape","abcdefgh","sesnape@htwsaar.de","06.08.1980"));
		userRepository.save(new UserEntity("siblack","asdfghjk","siblack@htwsaar.de","13.07.1982"));
		
		pictureRepository.save(new PictureEntity("src/main/resources/static/img/Gryffindor.jpg","Gryffindor-Wappen","Ein Hauswappen",userRepository.findByUserName("aldumbledore")));
		pictureRepository.save(new PictureEntity("src/main/resources/static/img/Hogwarts.jpg","Hogwarts-Schloss","Das Schloss",userRepository.findByUserName("sesnape")));
		pictureRepository.save(new PictureEntity("src/main/resources/static/img/HogwartsLogo.jpg","Hogwarts-Wappen","Ein anderes Wappen",userRepository.findByUserName("aldumbledore")));
		pictureRepository.save(new PictureEntity("src/main/resources/static/img/HufflepuffGemeinschaftsraum.jpg","Hufflepuff-Gemeinschaftsraum","ein Raum",userRepository.findByUserName("siblack")));
		pictureRepository.save(new PictureEntity("src/main/resources/static/img/RavenclawGemeinschaftsraum.jpg","Ravenclaw-Gemeinschaftsraum","Noch ein Raum",userRepository.findByUserName("siblack")));
		pictureRepository.save(new PictureEntity("src/main/resources/static/img/Slitherin.jpg","Slitherin-Wappen","Schon wieder ein Wappen",userRepository.findByUserName("aldumbledore")));
		pictureRepository.save(new PictureEntity("src/main/resources/static/img/SlitherinGemeinschaftsraum.jpg","Slitherin-Gemeinschaftsraum","Schon wieder ein Raum",userRepository.findByUserName("siblack")));
	}

}
