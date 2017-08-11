package demo;

import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Aparelho;
import bean.OrdemDeServico;
import bean.Peca;
import controllerJpa.AparelhoJpaController;
import controllerJpa.OrdemDeServicoJpaController;
import controllerJpa.PecaJpaController;
import javafx.application.Preloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import test.MoedaMask;
import util.Session;

public class CadAparelhoPopupController extends Preloader implements Initializable {

	@FXML
	private TextField textModelo;

	@FXML
	private TextField textSerial;

	@FXML
	private TextField textMarca;

	@FXML
	private TextField textNome;

	@FXML
	private TextField textNomePeca;
	@FXML
	private TextField textValor;

	private Aparelho aparelho;
	private Peca peca;
	@FXML
	private MoedaMask textValorPeca;
	@FXML
	private MoedaMask textMaoDeObra;
	private List<String> listaDigito;
	private List<String> listaDigitosMaoDeObra;

	@FXML
	ListView<Peca> listViewPeca;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		this.aparelho = Session.getAparelho();
		textMarca.setText(aparelho.getMarca());
		textSerial.setText(aparelho.getSerial());
		textModelo.setText(aparelho.getModelo());
		textValor.textProperty().addListener((obs, oldText, newText) -> {
			boolean ctl = false;
			String pattern = ".";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(newText);
			listaDigito = new ArrayList<String>();
			while (m.find()) {
				String str = m.group();
				if (!str.equals("$") && !str.equals("R") && !str.equals(" ")) {
					listaDigito.add(str);
				}
				System.out.println(m.group() + " " + (listaDigito.size() - 1));
			}

			if (listaDigito.size() == 1) {
				textValor.setText("R$ " + "0,0" + listaDigito.get(0));
			}
			if (listaDigito.size() == 5) {

				if (listaDigito.get(listaDigito.size() - 3).equals("0") && !ctl)

					if (listaDigito.get(listaDigito.size() - 1).equals("0")
							&& listaDigito.get(listaDigito.size() - 2).equals("0")
							&& listaDigito.get(listaDigito.size() - 3).equals("0"))

					{

						textValor.setText("R$ " + listaDigito.get(0) + "0," + listaDigito.get(listaDigito.size() - 2)
								+ listaDigito.get(listaDigito.size() - 1));
					} else
						textValor.setText("R$ " + "0," + listaDigito.get(listaDigito.size() - 2)
								+ listaDigito.get(listaDigito.size() - 1));

				else if (!listaDigito.get(0).equals("0") && !listaDigito.get(1).equals("0") && ctl == false) {

					String spl[] = newText.split(",");

					Pattern r1 = Pattern.compile(".");

					String strv = spl[0];
					String srtvRemove = strv.replace(" ", "");
					String strvCifra = srtvRemove.replace("$", "");
					String strvR = strvCifra.replace("R", "");
					Matcher m1 = r1.matcher(strvR);

					int cont = 0;
					while (m1.find()) {
						String str = m1.group();
						cont++;
					}

					if (!listaDigito.get(2).equals(",") && cont == 3) {
						textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(1)
								+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(2) + listaDigito.get(4));
					} else if (!listaDigito.get(2).equals(","))
						textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(2) + ","
								+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));

					ctl = true;
					listaDigito = new ArrayList<String>();

				} else if (!listaDigito.get(0).equals("0") && !ctl && !listaDigito.get(1).equals("0")) {
					textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(2) + "," + "00");

				}

				else {
					if (!listaDigito.get(1).equals("0"))
						textValor.setText("R$ " + listaDigito.get(listaDigito.size() - 3) + ","
								+ listaDigito.get(listaDigito.size() - 2) + ""
								+ listaDigito.get(listaDigito.size() - 1));
				}
			}

			if (listaDigito.size() == 6) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(3) + ","
							+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));

				}

			}

			if (listaDigito.size() == 7) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textValor.setText("R$ " + listaDigito.get(0) + "." + listaDigito.get(1) + listaDigito.get(2)
							+ listaDigito.get(4) + "," + listaDigito.get(listaDigito.size() - 2)
							+ listaDigito.get(listaDigito.size() - 1));

				}

			}

			if (listaDigito.size() == 8) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(2)
							+ listaDigito.get(3) + listaDigito.get(5) + "," + listaDigito.get(listaDigito.size() - 2)
							+ listaDigito.get(listaDigito.size() - 1));

				}
			}

			if (listaDigito.size() == 9) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(2)
							+ listaDigito.get(3) + listaDigito.get(4) + listaDigito.get(6) + ","
							+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));

				}

				// mudar marcao de mil
				if (listaDigito.get(1).equals(".")) {
					textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(2) + listaDigito.get(1)
							+ listaDigito.get(3) + listaDigito.get(4) + listaDigito.get(5) + ","
							+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));
				}

			}

			if (listaDigito.size() == 10) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(2)
							+ listaDigito.get(3) + listaDigito.get(4) + listaDigito.get(5) + listaDigito.get(7) + ","
							+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));

				}

				System.out.println("depois");
				if (listaDigito.get(2).equals(".")) {
					textValor.setText("R$ " + listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(3)
							+ listaDigito.get(2) + listaDigito.get(4) + listaDigito.get(5) + listaDigito.get(6) + ","
							+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));
				}
				System.out.println("aquiiii");

			}

			if (listaDigito.size() == 11) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {
					textValor.setText("R$ " + listaDigito.get(0) + "." + listaDigito.get(1)

							+ listaDigito.get(2) + listaDigito.get(4) + "." + listaDigito.get(5) + listaDigito.get(6)
							+ listaDigito.get(8) + "," + listaDigito.get(listaDigito.size() - 2)
							+ listaDigito.get(listaDigito.size() - 1));
				}
			}
			if (listaDigito.size() > 12) {
				textValor.setText("R$ " + "0,00");
			}
			// ...
			System.out.println("Text changed from " + oldText + " to " + newText);
		});

		Float valor = new Float("0.00");
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String formatado = nf.format(valor);
		textMaoDeObra.setText(formatado);

		textMaoDeObra.textProperty().addListener((obs, oldText, newText) -> {
			boolean ctl = false;
			String pattern = ".";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(newText);
			listaDigitosMaoDeObra = new ArrayList<String>();
			while (m.find()) {
				String str = m.group();
				if (!str.equals("$") && !str.equals("R") && !str.equals(" ")) {
					listaDigitosMaoDeObra.add(str);
				}
				System.out.println(m.group() + " " + (listaDigitosMaoDeObra.size() - 1));
			}

			if (listaDigitosMaoDeObra.size() == 1) {
				textMaoDeObra.setText("R$ " + "0,0" + listaDigitosMaoDeObra.get(0));
			}
			if (listaDigitosMaoDeObra.size() == 5) {

				if (listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3).equals("0") && !ctl)

					if (listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1).equals("0")
							&& listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2).equals("0")
							&& listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3).equals("0"))

					{

						textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + "0,"
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
					} else
						textMaoDeObra.setText("R$ " + "0," + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				else if (!listaDigitosMaoDeObra.get(0).equals("0") && !listaDigitosMaoDeObra.get(1).equals("0")
						&& ctl == false) {

					String spl[] = newText.split(",");

					Pattern r1 = Pattern.compile(".");

					String strv = spl[0];
					String srtvRemove = strv.replace(" ", "");
					String strvCifra = srtvRemove.replace("$", "");
					String strvR = strvCifra.replace("R", "");
					Matcher m1 = r1.matcher(strvR);

					int cont = 0;
					while (m1.find()) {
						String str = m1.group();
						cont++;
					}

					if (!listaDigitosMaoDeObra.get(2).equals(",") && cont == 3) {
						textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1)
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
								+ listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(4));
					} else if (!listaDigitosMaoDeObra.get(2).equals(","))
						textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(2) + ","
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

					ctl = true;
					listaDigitosMaoDeObra = new ArrayList<String>();

				} else if (!listaDigitosMaoDeObra.get(0).equals("0") && !ctl
						&& !listaDigitosMaoDeObra.get(1).equals("0")) {
					textMaoDeObra
							.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(2) + "," + "00");

				}

				else {
					if (!listaDigitosMaoDeObra.get(1).equals("0"))
						textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3) + ","
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2) + ""
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
				}
			}

			if (listaDigitosMaoDeObra.size() == 6) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1)
							+ listaDigitosMaoDeObra.get(3) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}

			}

			if (listaDigitosMaoDeObra.size() == 7) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + "." + listaDigitosMaoDeObra.get(1)
							+ listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(4) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}

			}

			if (listaDigitosMaoDeObra.size() == 8) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1)
							+ listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(5)
							+ "," + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}
			}

			if (listaDigitosMaoDeObra.size() == 9) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1)
							+ listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(4)
							+ listaDigitosMaoDeObra.get(6) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}

				// mudar marcao de mil
				if (listaDigitosMaoDeObra.get(1).equals(".")) {
					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(2)
							+ listaDigitosMaoDeObra.get(1) + listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(4)
							+ listaDigitosMaoDeObra.get(5) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
				}

			}

			if (listaDigitosMaoDeObra.size() == 10) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1)
							+ listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(4)
							+ listaDigitosMaoDeObra.get(5) + listaDigitosMaoDeObra.get(7) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}

				System.out.println("depois");
				if (listaDigitosMaoDeObra.get(2).equals(".")) {
					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1)
							+ listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(4)
							+ listaDigitosMaoDeObra.get(5) + listaDigitosMaoDeObra.get(6) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
				}
				System.out.println("aquiiii");

			}

			if (listaDigitosMaoDeObra.size() == 11) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {
					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + "." + listaDigitosMaoDeObra.get(1)

							+ listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(4) + "."
							+ listaDigitosMaoDeObra.get(5) + listaDigitosMaoDeObra.get(6) + listaDigitosMaoDeObra.get(8)
							+ "," + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
				}
			}
			if (listaDigitosMaoDeObra.size() > 12) {
				textMaoDeObra.setText("R$ " + "0,00");
			}
			// ...
			System.out.println("Text changed from " + oldText + " to " + newText);
		});

		listViewPeca.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				peca = listViewPeca.getSelectionModel().getSelectedItem();
				
				textNome.setText(peca.getNome());
				
				Float valor = new Float(peca.getPreco());
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				String formatado = nf.format(valor);
				textValor.setText(formatado);
			}
		});
		AparelhoJpaController aJpa = new AparelhoJpaController();

		List<Peca> listPecas = aJpa.findPecas(aparelho).getListaPeca();

		for (Peca p : listPecas) {
			listViewPeca.getItems().add(p);
		}

	}

	public boolean doEquals(String it) {
		String s = it.replace(".", "");
		String str = s.replaceAll(",", "");
		String vlrReal = str.replace("R", "");
		String vlrCifrao = vlrReal.replace("$", "");
		int i = 0;
		try {
			i = Integer.parseInt(vlrCifrao);
		} catch (NumberFormatException e) {
			return false;
		}

		if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 6 || i == 7 || i == 8
				|| i == 9) {
			return true;
		}
		return false;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	@FXML
	public void addPeca() {

		Peca peca = new Peca();
		peca.setNome(textNome.getText());

		String valor = textValor.getText();
		String toInt = valor.replace(".", "");
		String valorTr = toInt.replace(",", ".");
		String valorReal = valorTr.replace("R", "");
		String valorCifrao = valorReal.replace("$", "");
		Float preco = Float.parseFloat(valorCifrao);

		System.out.println("<><>==========<><><>");
		System.out.println(preco);
		peca.setPreco(preco);

		List<Peca> listPecas = new ArrayList<Peca>();
		listPecas.add(peca);

		PecaJpaController pecaJpa = new PecaJpaController();
		pecaJpa.create(peca);

		aparelho.setListaPeca(listPecas);

		AparelhoJpaController aparelhoJpaController = new AparelhoJpaController();
		aparelhoJpaController.merge(aparelho);
		listViewPeca.getItems().add(peca);

	}

}
