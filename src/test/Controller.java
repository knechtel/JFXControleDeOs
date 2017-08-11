package test;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.fabric.xmlrpc.base.Array;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller extends AnchorPane implements Initializable {

	@FXML
	private MoedaMask textMaoDeObra;

	private List<String> listaDigitosMaoDeObra;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("inicio");

		Float valor = new Float("123.45");
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

						textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + "0," + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
					} else
						textMaoDeObra.setText("R$ " + "0," + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				else if (!listaDigitosMaoDeObra.get(0).equals("0") && !listaDigitosMaoDeObra.get(1).equals("0") && ctl == false) {

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
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2) + listaDigitosMaoDeObra.get(2)+listaDigitosMaoDeObra.get(4));
					} else if (!listaDigitosMaoDeObra.get(2).equals(","))
						textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(2) + ","
								+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2) + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

					ctl = true;
					listaDigitosMaoDeObra = new ArrayList<String>();

				} else if (!listaDigitosMaoDeObra.get(0).equals("0") && !ctl && !listaDigitosMaoDeObra.get(1).equals("0")) {
					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(2) + "," + "00");

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

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1) + listaDigitosMaoDeObra.get(3) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2) + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}

			}

			if (listaDigitosMaoDeObra.size() == 7) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + "." + listaDigitosMaoDeObra.get(1) + listaDigitosMaoDeObra.get(2)
							+ listaDigitosMaoDeObra.get(4) + "," + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}

			}

			if (listaDigitosMaoDeObra.size() == 8) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1) + listaDigitosMaoDeObra.get(2)
							+ listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(5) + "," + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}
			}

			if (listaDigitosMaoDeObra.size() == 9) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1) + listaDigitosMaoDeObra.get(2)
							+ listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(4) + listaDigitosMaoDeObra.get(6) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2) + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}

				// mudar marcao de mil
				if (listaDigitosMaoDeObra.get(1).equals(".")) {
					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(1)
							+ listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(4) + listaDigitosMaoDeObra.get(5) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2) + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
				}

			}

			if (listaDigitosMaoDeObra.size() == 10) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {

					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1) + listaDigitosMaoDeObra.get(2)
							+ listaDigitosMaoDeObra.get(3) + listaDigitosMaoDeObra.get(4) + listaDigitosMaoDeObra.get(5) + listaDigitosMaoDeObra.get(7) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2) + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));

				}

				System.out.println("depois");
				if (listaDigitosMaoDeObra.get(2).equals(".")) {
					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + listaDigitosMaoDeObra.get(1) + listaDigitosMaoDeObra.get(3)
							+ listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(4) + listaDigitosMaoDeObra.get(5) + listaDigitosMaoDeObra.get(6) + ","
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2) + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
				}
				System.out.println("aquiiii");

			}

			if (listaDigitosMaoDeObra.size() == 11) {
				if (doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2))
						&& doEquals(listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 3))) {
					textMaoDeObra.setText("R$ " + listaDigitosMaoDeObra.get(0) + "." + listaDigitosMaoDeObra.get(1)

							+ listaDigitosMaoDeObra.get(2) + listaDigitosMaoDeObra.get(4) + "." + listaDigitosMaoDeObra.get(5) + listaDigitosMaoDeObra.get(6)
							+ listaDigitosMaoDeObra.get(8) + "," + listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 2)
							+ listaDigitosMaoDeObra.get(listaDigitosMaoDeObra.size() - 1));
				}
			}
			if (listaDigitosMaoDeObra.size() > 12) {
				textMaoDeObra.setText("R$ " + "0,00");
			}
			// ...
			System.out.println("Text changed from " + oldText + " to " + newText);
		});
	}

	public boolean doEquals(String it) {
		String s = it.replace(".", "");
		String str = s.replaceAll(",", "");
		String vlrReal = str.replace("R", "");
		String vlrCifrao = vlrReal.replace("$", "");
		int i = 0;
		try{
			 i = Integer.parseInt(vlrCifrao);
		}catch (NumberFormatException e) {
			return false;
		}
		
		if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 6 || i == 7 || i == 8
				|| i == 9) {
			return true;
		}
		return false;
	}
}
