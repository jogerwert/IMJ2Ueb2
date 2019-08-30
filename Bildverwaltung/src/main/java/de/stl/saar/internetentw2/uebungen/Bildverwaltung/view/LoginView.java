package de.stl.saar.internetentw2.uebungen.Bildverwaltung.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import de.stl.saar.internetentw2.uebungen.Bildverwaltung.entities.interfaces.User;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.forms.UserForm;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.classes.UserServiceImpl;
import de.stl.saar.internetentw2.uebungen.Bildverwaltung.service.interfaces.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;

@SpringView(name="login")
public class LoginView extends TabSheet
						implements View{
	private static final long serialVersionUID = 7007743112493555942L;
	
	protected static final String PICTUREVIEW = "pictures";
	
	@Autowired
	private UserService userService;

	public LoginView() {
		FormLayout loginLayout = new FormLayout();
		initializeLoginLayout(loginLayout);
		
		FormLayout registerLayout = new FormLayout();
		initializeRegisterLayout(registerLayout);
		
		Tab tabLogin = addTab(loginLayout, "Login");
		Tab tabRegister = addTab(registerLayout, "Registrieren");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	private void initializeLoginLayout(FormLayout loginLayout) {
		
		Binder<UserForm> userFormBinder = new Binder<>(UserForm.class);
		
		TextField txtLoginUserName = new TextField("Benutzername");
		loginLayout.addComponent(txtLoginUserName);
		
		PasswordField pwdLoginPassword = new PasswordField("Passwort");
		loginLayout.addComponent(pwdLoginPassword);
		
		userFormBinder.forField(txtLoginUserName)
		.withValidator( (String userName) -> !userName.trim().isEmpty(), 
				"Der Nutzername darf nicht leer sein!")
		.bind(UserForm::getUserName, UserForm::setUserName);
		
		userFormBinder.forField(pwdLoginPassword)
		.withValidator( (String password) -> password.length() >= 8,
				"Das Passwort darf nicht kürzer als 8 Zeichen sein!")
		.bind(UserForm::getUserPassword, UserForm::setUserPassword);
		
		Button btnLogin = new Button("Einloggen");
		btnLogin.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 4075600134357908383L;

			@Override
			public void buttonClick(ClickEvent clickEvent) {
				UserForm userForm = new UserForm();
				try {
					userFormBinder.writeBean(userForm);
					if(userFormBinder.validate().isOk()) {
						Navigator navigator = UI.getCurrent().getNavigator();
						NavigatorUI navUI = (NavigatorUI) UI.getCurrent();
						
						String userName = userForm.getUserName();
						String userPassword = userForm.getUserPassword();
						
						if(userService.authenticateUser(userName, userPassword)) {
							
							User currentUser = userService.findByUserName(userForm.getUserName());
							navUI.setCurrentUser(currentUser);
							navigator.navigateTo(PICTUREVIEW);
							
						} else {
							Notification.show("Nutzername und Passwort stimmen nicht überein!", 
									Type.WARNING_MESSAGE);
						}
					}
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		loginLayout.addComponent(btnLogin);
	}
	
	private void initializeRegisterLayout(FormLayout registerLayout) {
		
		Binder<UserForm> userFormBinder = new Binder<>(UserForm.class);
		
		TextField txtRegisterUserName = new TextField("Benutzername");
		registerLayout.addComponent(txtRegisterUserName);
		
		PasswordField pwdRegisterPassword = new PasswordField("Passwort");
		registerLayout.addComponent(pwdRegisterPassword);
		
		TextField txtRegisterEMail = new TextField("Emailadresse");
		registerLayout.addComponent(txtRegisterEMail);
		
		DateField dfRegisterBirthday = new DateField("Geburtsdatum");
		LocalDate today = LocalDate.now();
		dfRegisterBirthday.setRangeEnd(today);
		
		registerLayout.addComponent(dfRegisterBirthday);
		
		userFormBinder.forField(txtRegisterUserName).
		withValidator( (String userName) -> !userName.trim().isEmpty(), 
				"Der Nutzername darf nicht leer sein!")
		.withValidator( (String userName) -> userService.checkUserNameAvailable(userName),
				"Der Nutzername ist bereits vergeben!")
		.bind(UserForm::getUserName, UserForm::setUserName);
		
		userFormBinder.forField(pwdRegisterPassword)
		.withValidator( (String password) -> password.length() >= 8,
				"Das Passwort darf nicht kürzer als 8 Zeichen sein!")
		.bind(UserForm::getUserPassword, UserForm::setUserPassword);
		
		userFormBinder.forField(txtRegisterEMail)
		.withValidator(new EmailValidator("Es muss eine gültige Emailadresse eingegeben werden"))
		.bind(UserForm::getUserEmail, UserForm::setUserEmail);
		
		userFormBinder.forField(dfRegisterBirthday).bind(UserForm::getDateOfBirth, UserForm::setDateOfBirth);
		
		Button btnRegister = new Button("Registrieren");
		btnRegister.addClickListener(new ClickListener() {

			private static final long serialVersionUID = -3374071865081924408L;

			@Override
			public void buttonClick(ClickEvent event) {
				UserForm userForm = new UserForm();
				try {
					userFormBinder.writeBean(userForm);
					if(userFormBinder.validate().isOk()) {
						Navigator navigator = UI.getCurrent().getNavigator();
						User currentUser;
						NavigatorUI navUI = (NavigatorUI) UI.getCurrent();
						
						String userName = userForm.getUserName();
						String userPassword = userForm.getUserPassword();
						String userEmail = userForm.getUserEmail();
						
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
						String dateOfBirth = userForm.getDateOfBirth().format(formatter);
						
						currentUser = userService.createUser(userName, userPassword, userEmail, dateOfBirth);
						userService.saveUser(currentUser);
						navUI.setCurrentUser(currentUser);
						
						navigator.navigateTo(PICTUREVIEW);
					}
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		registerLayout.addComponent(btnRegister);
	}

}
