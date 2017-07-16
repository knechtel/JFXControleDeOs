package application;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import demo.CadOsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;

public class Principal extends Application {

	private Stage stage;
	private User loggedUser;
	

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/userLogin.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.setFill(new Color(0, 0, 0, 0));
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		stage.setResizable(false);
		
		

	}

	public static void main(String[] args) {
		launch(args);
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setUser(User user) {
		loggedUser = user;
	}

	void gotoProfile() {
		try {
			CadOsController profile = (CadOsController) replaceSceneContent("/view/Profile.fxml");
			profile.setApp(this);
		} catch (Exception ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = Principal.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(Principal.class.getResource(fxml));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		Scene scene = new Scene(page, 800, 600);
		stage.setScene(scene);
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}
}
