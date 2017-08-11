/*
    * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Dialogs;

import application.Principal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author herudi
 */
public class UserLoginController implements Initializable {
	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPass;

	Stage stage;

	private Principal application;

	public void setApp(Principal application) {
		this.application = application;
	}

	/**
	 * Initializes the controller class.
	 * 
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void aksiLogin(ActionEvent event) throws IOException {
		if (false) {
			Dialogs.create().owner(stage).title("Information").masthead("Informasi Data Baju")
					.message("Admin Harus Dipilih").showInformation();
		} else if (true) {

			// fecha janela anterior
			final Node source = (Node) event.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cadOs.fxml"));

			Scene newScene = null;
			try {
				newScene = new Scene(loader.load());
				newScene.getStylesheets().add("/application/baju.css");
			} catch (IOException ex) {
				// TODO: handle error
				ex.printStackTrace();
			}

			Stage inputStage = new Stage();
			inputStage.setScene(newScene);

			

			inputStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					System.exit(0);
				}
			});
			inputStage.setResizable(false);
			inputStage.show();
			System.out.println("aquiiiiii..!!!");

			System.out.println("mais uma vez");

		} else {
			Dialogs.create().owner(stage).title("Information").masthead("Informasi Data Baju")
					.message("Username Dan Password Tidak Cocok").showInformation();
			txtUser.clear();
			txtPass.clear();
			txtUser.requestFocus();
		}

	}

	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);
		scene.getStylesheets().setAll(getClass().getResource("/css/baju.css").toExternalForm());
		return scene;
	}

	@FXML
	private void aksiClose(ActionEvent event) {
		Platform.exit();
	}

}
