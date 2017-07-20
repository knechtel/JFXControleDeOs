package demo;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JList;

import application.Principal;
import bean.Aparelho;
import bean.Cidade;
import controllerJpa.CidadeJpaController;
import controllerJpa.ClienteJpaController;
import controllerJpa.OrdemDeServicoJpaController;

import javafx.application.Preloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CadCidadeController extends Preloader implements Initializable {
	@FXML
	private Button buttonOs;
	@FXML
	private Button buttonSalvar;
	@FXML
	private Button buttonDelete;
	@FXML
	private Button buttonNewOs;
	@FXML
	private TextField textCidade;

	@FXML
	private ListView<Cidade> listViewCidade;
	private Cidade cidade;
	private boolean newOS = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CidadeJpaController cJpa = new CidadeJpaController();
		for (Cidade c : cJpa.findAll()) {
			listViewCidade.getItems().add(c);
		}
		listViewCidade.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				newOS = false;
				cidade = listViewCidade.getSelectionModel().getSelectedItem();

				textCidade.setText(cidade.getNome());
			}

		});
	}

	public void start(Stage primaryStage) throws Exception {

	}

	@FXML
	private void doSave() {
	

		if (newOS) {
			if (!textCidade.getText().equals("")) {
				Cidade c = new Cidade();
				CidadeJpaController jpaController = new CidadeJpaController();

				c.setNome(textCidade.getText());
				jpaController.create(c);
				listViewCidade.getItems().add(c);
				System.out.println("SAVE register");
			}
		}else{
			cidade.setNome(textCidade.getText());
			CidadeJpaController jpaController = new CidadeJpaController();
			jpaController.edit(cidade);
		}

	}

	@FXML
	private void doOS(ActionEvent actionEvent) {
		try {
			Parent root;
			Stage stage;
			// CadCidadeController profile = (CadCidadeController)
			// replaceSceneContent("/view/cadCidade.fxml");
			stage = (Stage) buttonOs.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/view/cadOs.fxml"));
			Scene s = new Scene(root);
			stage.setScene(s);
			stage.show();
		} catch (Exception ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void newOsAct() {
		newOS = true;
		textCidade.setText("");
	}
	@FXML
	private void doDelete(){
		CidadeJpaController cidadeJpa= new CidadeJpaController();
		cidadeJpa.delete(cidade);
		textCidade.setText("");
		listViewCidade.getItems().remove(cidade);
		
	}

}
