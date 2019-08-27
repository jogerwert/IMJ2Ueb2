package de.stl.saar.internetentw2.uebungen.Bildverwaltung.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringView(name="pictures")
public class PictureView extends TabSheet
						implements View{
	
	private static final long serialVersionUID = 6579452375615587856L;
	
	protected static final String LOGINVIEW = "login";

	public PictureView() {
		GridLayout myPicturesLayout = new GridLayout();
		initializeMyPicturesLayout(myPicturesLayout);
		
		FormLayout uploadPictureLayout = new FormLayout();
		initializeUploadPictureLayout(uploadPictureLayout);
		
		GridLayout  sharedPicturesLayout = new GridLayout();
		initializeSharedPicturesLayout(sharedPicturesLayout);
		
		VerticalLayout logoutLayout = new VerticalLayout();
		initializeLogoutLayout(logoutLayout);
		
		Tab tabMyPictures = addTab(myPicturesLayout, "Meine Fotos");
		Tab tabUploadPicture = addTab(uploadPictureLayout, "Fotos hochladen");
		Tab tabSharedPictures = addTab(sharedPicturesLayout, "Freigegebene Bilder");
		Tab tabLogout = addTab(logoutLayout, "Ausloggen");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	private void initializeUploadPictureLayout(FormLayout uploadPictureLayout) {
		//TODO
	}
	
	private void initializeMyPicturesLayout(GridLayout myPicturesLayout) {
		//TODO
	}
	
	private void initializeSharedPicturesLayout(GridLayout sharedPicturesLayout) {
		//TODO
	}
	
	private void initializeLogoutLayout(VerticalLayout logoutLayout) {
		
		Button btnLogout = new Button("Ausloggen");
		btnLogout.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 4526869093274741013L;

			@Override
			public void buttonClick(ClickEvent event) {
				Navigator navigator = UI.getCurrent().getNavigator();
				NavigatorUI navUI = (NavigatorUI) UI.getCurrent();
				
				navUI.setCurrentUser(null);
				navigator.navigateTo(LOGINVIEW);
			}
			
		});
		
		logoutLayout.addComponent(btnLogout);
	}

}
