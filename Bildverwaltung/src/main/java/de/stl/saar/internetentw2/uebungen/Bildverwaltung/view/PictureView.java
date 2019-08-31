package de.stl.saar.internetentw2.uebungen.Bildverwaltung.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.event.selection.MultiSelectionEvent;
import com.vaadin.event.selection.MultiSelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.VerticalLayout;


import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.Picture;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.forms.PictureForm;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces.PictureService;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces.UserService;

@SpringView(name="pictures")
public class PictureView extends TabSheet
						implements View{
	private static final long serialVersionUID = 6579452375615587856L;
	
	protected static final String LOGINVIEW = "login";
	
	private final String PICTURE_PATH = "upload" + File.separator + "img";
	
	private final float IMAGE_HEIGHT = 150.00f;
	private final float IMAGE_WIDTH = 150.00f;
	
	@Autowired
	private SpringNavigator navigator;
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private UserService userService;
	
	private User currentUser;

	public PictureView() {
		
	}
	
	@PostConstruct
	public void init() {
		NavigatorUI navUI = (NavigatorUI) UI.getCurrent();
		
		User checkUser = navUI.getCurrentUser();
		if(checkUser == null) {
			Notification.show("Bitte zuerst einloggen!", Type.ERROR_MESSAGE);
			navigator.navigateTo(LOGINVIEW);
		}else {
			
			this.currentUser = checkUser;
		
			GridLayout myPicturesLayout = new GridLayout(3, 1);
			initializeMyPicturesLayout(myPicturesLayout);
			
			FormLayout uploadPictureLayout = new FormLayout();
			initializeUploadPictureLayout(uploadPictureLayout, myPicturesLayout);
			
			GridLayout  sharedPicturesLayout = new GridLayout(3, 1);
			initializeSharedPicturesLayout(sharedPicturesLayout);
			
			VerticalLayout logoutLayout = new VerticalLayout();
			initializeLogoutLayout(logoutLayout);
			
			Tab tabMyPictures = addTab(myPicturesLayout, "Meine Fotos");
			Tab tabUploadPicture = addTab(uploadPictureLayout, "Fotos hochladen");
			Tab tabSharedPictures = addTab(sharedPicturesLayout, "Freigegebene Bilder");
			Tab tabLogout = addTab(logoutLayout, "Ausloggen");
		}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	private void initializeUploadPictureLayout(FormLayout uploadPictureLayout, GridLayout myPicturesLayout) {
		
		Binder<PictureForm> pictureFormBinder = new Binder<>(PictureForm.class);
		
		final String[] pathArray = new String[1];
		pathArray[0] = null;
		
		Receiver receiver = new Receiver() {
			private static final long serialVersionUID = 8875344360055059636L;

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				try {
					String userName = currentUser.getUserName();
					String directoryName = PICTURE_PATH + File.separator + userName;
					
					File directory = new File(directoryName);
					directory.mkdirs();
					
					File tempFile = new File(directoryName, filename);
					if(tempFile.exists()) {
						Notification.show("Ein Bild mit diesem Namen existiert bereits!", 
								Type.WARNING_MESSAGE);
						return null;
					} else {
						tempFile.createNewFile();
					}
					return new FileOutputStream(tempFile);
				}catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		
		Upload upload = new Upload("Foto hochladen", receiver);
		upload.addSucceededListener(new Upload.SucceededListener() {
			private static final long serialVersionUID = 1276870351256882367L;

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				String userName = currentUser.getUserName();
				String filename = event.getFilename();
				String path = PICTURE_PATH + File.separator + userName + File.separator + filename;
				
				pathArray[0] = path;
				
				
				Notification.show("Bild erfolgreich hochgeladen", Type.HUMANIZED_MESSAGE);
			}
		});
		
		uploadPictureLayout.addComponent(upload);
		
		TextField txtTitle = new TextField("Titel");
		uploadPictureLayout.addComponent(txtTitle);
		
		TextArea txtDescription = new TextArea("Beschreibung");
		uploadPictureLayout.addComponent(txtDescription);
		
		pictureFormBinder.forField(txtTitle)
		.withValidator( (String title) -> !title.trim().isEmpty(), 
				"Der Titel darf nicht leer sein!")
		.withValidator( (String title) -> pictureService.checkTitleAvailable(title),
				"Der Titel wird bereits verwendet!")
		.bind(PictureForm::getTitle, PictureForm::setTitle);
		
		pictureFormBinder.forField(txtDescription)
		.bind(PictureForm::getDescription, PictureForm::setDescription);
		
		Button btnSave = new Button("Bild speichern");
		btnSave.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 6015228795955508552L;

			@Override
			public void buttonClick(ClickEvent event) {
				PictureForm pictureForm = new PictureForm();
				try {
					pictureFormBinder.writeBean(pictureForm);
					
					if(pathArray[0] == null) {
						Notification.show("Bitte zuerst ein Bild hochladen!", 
								Type.WARNING_MESSAGE);
					} else if(!pictureService.checkPathAvailable(pathArray[0])){
						Notification.show("Ein Bild mit diesem Namen wurde bereits gespeichert!", Type.WARNING_MESSAGE);
					} else {
						String picturePath = pathArray[0];
						String title = pictureForm.getTitle();
						String description = pictureForm.getDescription();
						User owner = currentUser;
						List<User> releaseList = new ArrayList<User>();
						
						Picture picture = 
								pictureService.createPicture(picturePath, title, description, owner, releaseList);
						pictureService.savePicture(picture);
						
						VerticalLayout pictureLayout = new VerticalLayout();
						createSinglePictureDisplay(pictureLayout, picture);
						myPicturesLayout.addComponent(pictureLayout);
						
						Notification.show("Bild erfolgreich gespeichert", Type.HUMANIZED_MESSAGE);
					}
					
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		uploadPictureLayout.addComponent(btnSave);
		
	}
	
	private void initializeMyPicturesLayout(GridLayout myPicturesLayout) {
		reloadMyPictures(myPicturesLayout);
	}
	
	private void reloadMyPictures(GridLayout myPicturesLayout) {
		List<Picture> myPicturesList = pictureService.findByOwner(currentUser);
		
		myPicturesLayout.removeAllComponents();
		for (Picture picture : myPicturesList) {
			VerticalLayout pictureLayout = new VerticalLayout();
			createSinglePictureDisplay(pictureLayout, picture);
			myPicturesLayout.addComponent(pictureLayout);
		}
	}
	
	private void createSinglePictureDisplay(VerticalLayout pictureLayout, Picture picture) {
		String title = picture.getTitle();
		String description = picture.getDescription();
		List<User> userList = userService.findAllUser();
		
		Image pictureComponent = createImageFromPicture(picture);
		pictureLayout.addComponent(pictureComponent);
		
		Label lblTitle = new Label(title);
		pictureLayout.addComponent(lblTitle);
		
		Label lblDescription = new Label(description);
		pictureLayout.addComponent(lblDescription);
		
		Button btnDelete = new Button("Löschen");
		btnDelete.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -6884133918023926301L;

			@Override
			public void buttonClick(ClickEvent event) {
				GridLayout parent = (GridLayout) pictureLayout.getParent();
				parent.removeComponent(pictureLayout);
				
				File pictureFile = new File(picture.getPicturePath());
				if(pictureFile.exists()) {
					pictureFile.delete();
				}
				
				pictureService.deletePicture(picture);
				
				Notification.show("Bild erfolgreich gelöscht", Type.HUMANIZED_MESSAGE);
			}
		});
		
		pictureLayout.addComponent(btnDelete);
		
		
		FormLayout shareLayout = new FormLayout();
		
		Button btnOpenShare = new Button("Freigeben");
		btnOpenShare.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4908487318079395117L;

			@Override
			public void buttonClick(ClickEvent event) {
				shareLayout.setVisible(true);
			}
		});
		pictureLayout.addComponent(btnOpenShare);
		
		
		List<User> shareList = new ArrayList<User>();
		List<User> otherUsersList = new ArrayList<User>();
		for (User user : userList) {
			if(user.getUserId() != currentUser.getUserId()) {
				otherUsersList.add(user);
			}
		}
		
		TwinColSelect<User> twinShareWith = new TwinColSelect<>();
		twinShareWith.setItems(otherUsersList);
		twinShareWith.addSelectionListener(new MultiSelectionListener<User>() {
			private static final long serialVersionUID = 9004150840293228440L;

			@Override
			public void selectionChange(MultiSelectionEvent<User> event) {
				shareList.clear();
				shareList.addAll(event.getAddedSelection());
			}
		});
		shareLayout.addComponent(twinShareWith);
		
		Button btnShare = new Button("Freigeben");
		btnShare.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4049168407669538249L;

			@Override
			public void buttonClick(ClickEvent event) {
				List<User> releaseList = picture.getRelease();
				releaseList.clear();
				releaseList.addAll(shareList);
				pictureService.savePicture(picture);
				shareLayout.setVisible(false);
			}
		});
		shareLayout.addComponent(btnShare);
		
		shareLayout.setVisible(false);
		pictureLayout.addComponent(shareLayout);
		
	}
	
	private void initializeSharedPicturesLayout(GridLayout sharedPicturesLayout) {
		reloadSharedPictures(sharedPicturesLayout);
	}
	
	private void reloadSharedPictures(GridLayout sharedPicturesLayout) {
		List<Picture> sharedPicturesList = pictureService.findByRelease(currentUser);
		
		sharedPicturesLayout.removeAllComponents();
		for (Picture picture : sharedPicturesList) {
			VerticalLayout sharedLayout = new VerticalLayout();
			createSingleSharedPictureDisplay(sharedLayout, picture);
			sharedPicturesLayout.addComponent(sharedLayout);
		}
	}
	
	private void createSingleSharedPictureDisplay(VerticalLayout sharedLayout, Picture picture) {
		String ownerName = picture.getOwner().getUserName();
		String title = picture.getTitle();
		String description = picture.getDescription();
		
		
		Image pictureComponent = createImageFromPicture(picture);
		sharedLayout.addComponent(pictureComponent);
		
		String strSharedBy = "Freigegeben von: " + ownerName;
		Label lblSharedBy = new Label(strSharedBy);
		sharedLayout.addComponent(lblSharedBy);
		
		String strTitle = "Titel: " + title;
		Label lblTitle = new Label(strTitle);
		sharedLayout.addComponent(lblTitle);
		
		String strDescription = "Beschreibung: " + description;
		Label lblDescription = new Label(strDescription);
		sharedLayout.addComponent(lblDescription);
		
	}
	
	private void initializeLogoutLayout(VerticalLayout logoutLayout) {
		
		Button btnLogout = new Button("Ausloggen");
		btnLogout.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 4526869093274741013L;

			@Override
			public void buttonClick(ClickEvent event) {
				NavigatorUI navUI = (NavigatorUI) UI.getCurrent();
				
				navUI.setCurrentUser(null);
				navigator.navigateTo(LOGINVIEW);
			}
			
		});
		
		logoutLayout.addComponent(btnLogout);
	}
	
	private Image createImageFromPicture(Picture picture) {
		File pictureFile = new File(picture.getPicturePath());
		Resource pictureResource = new FileResource(pictureFile);
		Image image = new Image("", pictureResource);
		image.setHeight(IMAGE_HEIGHT, Unit.PIXELS);
		image.setWidth(IMAGE_WIDTH, Unit.PIXELS);
		
		return image;
	}

}
