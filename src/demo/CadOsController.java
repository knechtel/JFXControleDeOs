
package demo;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.dialog.Dialogs;

import application.Principal;
import controllerJpa.ClienteJpaController;
import controllerJpa.OrdemDeServicoJpaController;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.Cliente;
import model.OrdemDeServico;
import model.User;

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
	TableColumn itemQtyCol;
	@FXML
	TableColumn itemId;
	@FXML
	TableColumn itemPriceCol;
	@FXML
	ListView<OrdemDeServico> listViewOs;

	@FXML
	TextField textDataEntrada;

	private OrdemDeServico os;
	private Cliente cliente;

	private boolean onNew = true;

	List<Item> list = new ArrayList<Item>();

	public class Item {
		public SimpleLongProperty id = new SimpleLongProperty();
		public SimpleStringProperty name = new SimpleStringProperty();
		public SimpleStringProperty modelo = new SimpleStringProperty();

		public Item() {

		}

		public Long getId() {
			return id.get();
		}

		public String getName() {
			return name.get();
		}

		public String getModelo() {
			return modelo.get();
		}

	}

	public void setApp(Principal application) {

		this.application = application;
		User loggedUser = application.getLoggedUser();
		textClienteNome.setText(loggedUser.getId());
		textEmail.setText(loggedUser.getEmail());
		subscribed.setSelected(loggedUser.isSubscribed());
		success.setOpacity(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("teste");
		itemTbl.setEditable(true);
		itemId.setEditable(true);
		itemIdCol.setCellValueFactory(new PropertyValueFactory<Item, Long>("id"));

		TableColumn<Cliente, Boolean> checkCol = new TableColumn<>("Check");
		checkCol.setCellValueFactory(new PropertyValueFactory<Cliente, Boolean>("checkBoxValue"));
		checkCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkCol));

		ClienteJpaController service = new ClienteJpaController();
		List<Item> list = new ArrayList<Item>();
		for (Cliente c : service.findAll()) {

			Item i = new Item();
			i.name.set(c.getNome());
			i.id.set(c.getId());
			i.modelo.set("LCD1578");
			list.add(i);
		}
		ObservableList<Item> oListStavaka = FXCollections.observableArrayList(list);
		itemTbl.setItems(oListStavaka);

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
					if (os.getDataEntrada() != null){
						DateFormat df =new SimpleDateFormat("dd/MM/YYYY");
						textDataEntrada.setText(df.format(os.getDataEntrada()));
					}
				} else {
					cleanFields();
				}
			}
		});

		itemNameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		itemNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		itemNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Item, String>>() {
			@Override
			public void handle(CellEditEvent<Item, String> t) {

				((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).name.set((t.getNewValue()));
			}
		});

		itemId.setCellValueFactory(new PropertyValueFactory<Book, String>("modelo"));
		itemId.setCellFactory(TextFieldTableCell.forTableColumn());
		itemId.setOnEditCommit(new EventHandler<CellEditEvent<Item, String>>() {
			@Override
			public void handle(CellEditEvent<Item, String> t) {

				((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).modelo.set((t.getNewValue()));
			}
		});

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

}
