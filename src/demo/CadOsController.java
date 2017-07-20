
package demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.dialog.Dialogs;

import application.Principal;
import bean.Aparelho;
import bean.Cliente;
import bean.OrdemDeServico;
import bean.User;
import controllerJpa.AparelhoJpaController;
import controllerJpa.ClienteJpaController;
import controllerJpa.OrdemDeServicoJpaController;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * CadOs Controller.
 */
public class CadOsController extends AnchorPane implements Initializable {

	@FXML
	private TextField textClienteNome;
	@FXML
	private TextField textTelefone;
	@FXML
	private TextField textEmail;
	@FXML
	private TextField textEndereco;
	@FXML
	private TextField textCelular;
	@FXML
	TextField textRG;
	@FXML
	TextField textCidade;
	@FXML
	private CheckBox subscribed;
	@FXML
	private Hyperlink logout;
	@FXML
	private Button saveButton;
	@FXML
	private Button buttonSalvarAparelho;
	@FXML
	private Button buttonSelect;

	@FXML
	private Button buttonCidade;
	@FXML
	private Button actItem;

	@FXML
	private TextField textCPF;

	@FXML
	private Label success;

	private Principal application;
	@FXML
	private TableView<Cliente> table;
	private List<Cliente> users;

	@FXML
	private TableView<Cliente> tableView;
	@FXML
	private TableColumn<Cliente, String> clienteName;
	// The table and columns
	@FXML
	TableView<Item> itemTbl;

	@FXML
	TableColumn itemIdCol;
	@FXML
	TableColumn itemNameCol;
	@FXML
	TableColumn itemAparelhoPronto;
	@FXML
	TableColumn itemId;
	@FXML
	TableColumn itemPriceCol;
	@FXML
	TableColumn itemSerial;
	@FXML
	TableColumn itemMarca;
	// @FXML
	// TableColumn itemPronto;
	

	@FXML
	ListView<OrdemDeServico> listViewOs;

	@FXML
	TextField textDataEntrada;
	@FXML
	private MenuItem menuCidade;

	private OrdemDeServico os;
	private Cliente cliente;
	private Item item;
	private boolean onNew = true;

	private List<Item> list = new ArrayList<Item>();

	private List<Aparelho> listAparelho = new ArrayList<Aparelho>();

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		itemTbl.setEditable(true);
		itemId.setEditable(true);
		itemIdCol.setCellValueFactory(new PropertyValueFactory<Item, Long>("id"));

		TableColumn<Cliente, Boolean> checkCol = new TableColumn<>("Check");
		checkCol.setCellValueFactory(new PropertyValueFactory<Cliente, Boolean>("checkBoxValue"));
		checkCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkCol));

		AparelhoJpaController jpaAparelho = new AparelhoJpaController();

		// clienteName.setCellValueFactory(new PropertyValueFactory<Cliente,
		// String>("clienteName"));

		BatchService service1 = new BatchService();

		// BatchService cjpa = new BatchService();
		// tableView.getItems().setAll(service.findAll());

		TextFields.bindAutoCompletion(textClienteNome, t -> {

			return service1.findByName(t.getUserText());
		});

		textClienteNome.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Integer index = 0;
				String name = textClienteNome.getText();

				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(name);
				while (m.find()) {
					System.out.println(m.group() + "aquiiii @!");

					index = Integer.parseInt(m.group());
				}

				ClienteJpaController clienteJpa = new ClienteJpaController();

				cliente = clienteJpa.findById(index);

				if (cliente.getEmail() != null)
					textEmail.setText(cliente.getEmail());

				if (cliente.getEndereco() != null)
					textEndereco.setText(cliente.getEndereco());

			}
		});

		OrdemDeServicoJpaController osJpa = new OrdemDeServicoJpaController();

		List<OrdemDeServico> list1 = osJpa.findAll();
		for (OrdemDeServico o : list1) {
			listViewOs.getItems().add(o);
		}

		listViewOs.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				os = listViewOs.getSelectionModel().getSelectedItem();
				onNew = false;

				OrdemDeServicoJpaController osJpa = new OrdemDeServicoJpaController();

				cliente = os.getCliente();

				if (cliente != null) {

					if (os.getCliente().getNome() != null)
						textClienteNome.setText(os.getCliente().getNome());

					if (os.getCliente().getCPF() != null)
						textCPF.setText(os.getCliente().getCPF());

					if (os.getCliente().getEndereco() != null)
						textEndereco.setText(os.getCliente().getEndereco());

					if (os.getCliente().getEmail() != null)
						textEmail.setText(os.getCliente().getEmail());

					if (os.getCliente().getTelefone() != null)
						textTelefone.setText(os.getCliente().getTelefone());

					if (os.getCliente().getRg() != null)
						textRG.setText(os.getCliente().getRg());

					if (os.getCliente().getCelular() != null)
						textCelular.setText(os.getCliente().getCelular());
					if (os.getDataEntrada() != null) {
						DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
						textDataEntrada.setText(df.format(os.getDataEntrada()));
					}

					if (os != null) {

						if (os.getId() != null) {
							OrdemDeServicoJpaController osJpaAparelho1 = new OrdemDeServicoJpaController();
							os = osJpaAparelho1.findByAparelho(os.getId());
							if (os.getListaAparelho() != null) {

								if (os.getListaAparelho().size() != list.size())
									for (Aparelho a : os.getListaAparelho()) {
										 item = new Item();

										item.modelo.set(a.getModelo());
										item.serial.set(a.getSerial());
										item.id.set(a.getId());
										item.marca.set(a.getMarca());
										item.pronto.set(true);
										item.autorizado.set(true);
										list.add(item);

									}

								ObservableList<Item> oListStavaka = FXCollections.observableArrayList(list);
								itemTbl.setItems(oListStavaka);

							}
						}
					}
				} else {
					cleanFields();
				}
			}
		});

		itemNameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Autorizado"));

		
		itemNameCol.setCellValueFactory(c-> new SimpleBooleanProperty(item.autorizado.getValue()));
		itemNameCol.setCellFactory(tc -> new CheckBoxTableCell<>());

		itemId.setCellValueFactory(new PropertyValueFactory<Book, String>("modelo"));
		itemId.setCellFactory(TextFieldTableCell.forTableColumn());
		itemId.setOnEditCommit(new EventHandler<CellEditEvent<Item, String>>() {
			@Override
			public void handle(CellEditEvent<Item, String> t) {

				((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).modelo.set((t.getNewValue()));
			}
		});

		itemSerial.setCellValueFactory(new PropertyValueFactory<Book, String>("serial"));
		itemSerial.setCellFactory(TextFieldTableCell.forTableColumn());
		itemSerial.setOnEditCommit(new EventHandler<CellEditEvent<Item, String>>() {
			@Override
			public void handle(CellEditEvent<Item, String> t) {

				((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).serial.set((t.getNewValue()));
			}
		});

		itemMarca.setCellValueFactory(new PropertyValueFactory<Book, String>("marca"));
		itemMarca.setCellFactory(TextFieldTableCell.forTableColumn());
		itemMarca.setOnEditCommit(new EventHandler<CellEditEvent<Item, String>>() {
			@Override
			public void handle(CellEditEvent<Item, String> t) {

				((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).marca.set((t.getNewValue()));
			}
		});

		// ItemAparelho ia =new ItemAparelho();
		// itemAparelhoPronto.setCellValueFactory(c -> new
		// SimpleBooleanProperty(c.getValue().getIsDefault()));
		itemAparelhoPronto.setCellFactory(tc -> new CheckBoxTableCell<>());

	
		
		itemAparelhoPronto.setCellValueFactory(c-> new SimpleBooleanProperty(item.pronto.getValue()));
		itemAparelhoPronto.setCellFactory(tc -> new CheckBoxTableCell<>());

		textDataEntrada.setEditable(false);

	}

	public void processLogout(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			return;
		}

	}

	public void saveProfile(ActionEvent event) {
		if (application == null) {
			// We are running in isolated FXML, possibly in Scene Builder.
			// NO-OP.
			animateMessage();
			return;
		}
		User loggedUser = application.getLoggedUser();
		loggedUser.setEmail(textEmail.getText());
		// loggedUser.setPhone(phone.getText());
		loggedUser.setSubscribed(subscribed.isSelected());

		animateMessage();
	}

	public void cleanFields() {
		onNew = true;
		textClienteNome.setText("");
		textEmail.setText("");
		textEndereco.setText("");
		textTelefone.setText("");
		textCPF.setText("");
		textRG.setText("");
		textCelular.setText("");
		textCidade.setText("");
	}

	public void resetProfile(ActionEvent event) {

		cleanFields();

		Dialogs.create().message("Pronto para cadastrar outro cliente!").showInformation();

	}

	private void animateMessage() {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), success);
		ft.setFromValue(0.0);
		ft.setToValue(1);
		ft.play();
	}

	@FXML
	private void aksiClose(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	public void handle(WindowEvent event) {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		Item item = new Item();
		item.id.setValue(9);
		item.name.setValue("Item Number " + item.id.getValue());

		// data.add(item);
	}

	@FXML
	private void actItem(ActionEvent event) {
		System.out.println("testando...");

		Item i = new Item();
		i.name.set("maiquel");
		i.modelo.set("LCD4209");
		i.serial.set("number2592929");
		i.marca.set("LG");
		i.id.set(5);
		list.add(i);

		ObservableList<Item> oListStavaka = FXCollections.observableArrayList(list);
		itemTbl.setItems(oListStavaka);
	}

	@SuppressWarnings("deprecation")
	@FXML
	private void saveAct(ActionEvent event) throws IOException {

		System.out.println("testando...");

		if (cliente == null)
			cliente = new Cliente();
		cliente.setNome(textClienteNome.getText());
		cliente.setEmail(textEmail.getText());
		cliente.setEndereco(textEndereco.getText());
		cliente.setTelefone(textTelefone.getText());
		cliente.setCPF(textCPF.getText());
		cliente.setRg(textRG.getText());
		cliente.setCelular(textCelular.getText());

		if (!textClienteNome.getText().equals("") && onNew) {

			textClienteNome.setText("");
			textEmail.setText("");
			textEndereco.setText("");
			textTelefone.setText("");
			textCPF.setText("");
			textRG.setText("");
			textCelular.setText("");
			textCidade.setText("");
			Dialogs.create().message("Cliente cadastrado com sucesso!").showInformation();

			ClienteJpaController clienteJpa = new ClienteJpaController();

			TextFields.bindAutoCompletion(textClienteNome, t -> {

				return clienteJpa.findByName(t.getUserText());
			});

			ClienteJpaController clienteJp1 = new ClienteJpaController();
			clienteJp1.create(cliente);

			if (os == null)
				os = new OrdemDeServico();
			os.setDataEntrada(new Date());
			os.setCliente(cliente);
			OrdemDeServicoJpaController osJpa = new OrdemDeServicoJpaController();
			osJpa.create(os);

			System.out.println("CREATE OBJECT");
		} else if (!onNew) {

			ClienteJpaController clienteJpaCtl = new ClienteJpaController();
			clienteJpaCtl.edit(cliente);
			System.out.println(cliente);

			OrdemDeServicoJpaController osJpa = new OrdemDeServicoJpaController();
			osJpa.edit(os);
			System.out.println("EDIT OBJECT");
			OrdemDeServicoJpaController osjpaUp = new OrdemDeServicoJpaController();
			listViewOs = new ListView<OrdemDeServico>();
			for (OrdemDeServico o1 : osjpaUp.findAll()) {
				listViewOs.getItems().add(o1);
			}

		} else {
			Dialogs.create().message("É necessário o preechimento ao menos do nome do cliente!").showInformation();

		}

	}

	@FXML
	public void doCidade(ActionEvent actionEvent) {
		try {
			Parent root;
			Stage stage;
			// CadCidadeController profile = (CadCidadeController)
			// replaceSceneContent("/view/cadCidade.fxml");
			stage = (Stage) buttonCidade.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/view/cadCity.fxml"));
			Scene s = new Scene(root);
			stage.setScene(s);
			stage.show();
		} catch (Exception ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("doCidade");
	}

	@FXML
	public void doOs() {
		try {
			Parent root;
			Stage stage;
			// CadCidadeController profile = (CadCidadeController)
			// replaceSceneContent("/view/cadCidade.fxml");
			stage = (Stage) buttonCidade.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/view/cadOs.fxml"));
			Scene s = new Scene(root);
			stage.setScene(s);
			stage.show();
		} catch (Exception ex) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	public void saveAparelho(ActionEvent event) {
		Aparelho a = new Aparelho();

		a.setModelo(itemMarca.getText());

		for (Item i : list) {
			System.out.println(i.getModelo());

			a.setModelo(i.getModelo());
			a.setSerial(i.getSerial());
			a.setMarca("Philips");
			AparelhoJpaController ajpa = new AparelhoJpaController();
			ajpa.create(a);
			listAparelho.add(a);
		}
		os.setListaAparelho(listAparelho);
		OrdemDeServicoJpaController osJpa = new OrdemDeServicoJpaController();
		osJpa.edit(os);
		System.out.println("salvar ..." + list.size());

	}
	
	@FXML
	public void getAct(ActionEvent event){
		
	//	System.out.println(itemTbl.getSelectionModel().getSelectedItem().id.get());
		System.out.println("test");
		System.out.println("mais um teste");
		try {
			   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/aparelhoPop.fxml"));
               Parent root1 = (Parent) fxmlLoader.load();
               Stage stage = new Stage();
               stage.initModality(Modality.APPLICATION_MODAL); 
               stage.setScene(new Scene(root1));  
               stage.showAndWait();
			
		} catch (Exception ex) {
			Logger.getLogger(CadOsController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/cadOs.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.setFill(new Color(0, 0, 0, 0));
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		stage.setResizable(false);

	}

}
