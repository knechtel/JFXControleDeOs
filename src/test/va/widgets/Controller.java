package test.va.widgets;

import java.net.URL;
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
import test.MoedaMask;

public class Controller extends AnchorPane implements Initializable {

	@FXML
	private MoedaMask textField;

	private List<String> listaDigito;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("inicio");

		textField.textProperty().addListener((obs, oldText, newText) -> {
			boolean ctl = false;
			String pattern = ".";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(newText);
			listaDigito = new ArrayList<String>();
			while (m.find()) {
				listaDigito.add(m.group());
				System.out.println(m.group() + " " + (listaDigito.size() - 1));
			}

			if (listaDigito.size() == 1) {
				textField.setText("0,0" + listaDigito.get(0));
			}
			if (listaDigito.size() == 5) {

				if (listaDigito.get(listaDigito.size() - 3).equals("0") && !ctl)

					if (listaDigito.get(listaDigito.size() - 1).equals("0")
							&& listaDigito.get(listaDigito.size() - 2).equals("0")
							&& listaDigito.get(listaDigito.size() - 3).equals("0"))

					{

						textField.setText(listaDigito.get(0) + "0," + listaDigito.get(listaDigito.size() - 2)
								+ listaDigito.get(listaDigito.size() - 1));
					} else
						textField.setText("0," + listaDigito.get(listaDigito.size() - 2)
								+ listaDigito.get(listaDigito.size() - 1));

				else if (!listaDigito.get(0).equals("0") && !listaDigito.get(1).equals("0") && ctl == false) {

					if (!listaDigito.get(2).equals(","))
						textField.setText(listaDigito.get(0) + listaDigito.get(2) + ","
								+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));
					ctl = true;
					listaDigito = new ArrayList<String>();

				} else if (!listaDigito.get(0).equals("0") && !ctl && !listaDigito.get(1).equals("0")) {
					textField.setText(listaDigito.get(0) + listaDigito.get(2) + "," + "00");

				}

				else {
					if (!listaDigito.get(1).equals("0"))
						textField.setText(
								listaDigito.get(listaDigito.size() - 3) + "," + listaDigito.get(listaDigito.size() - 2)
										+ "" + listaDigito.get(listaDigito.size() - 1));
				}
			}

			if (listaDigito.size() == 6) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textField.setText(listaDigito.get(0) +"." +listaDigito.get(1) + listaDigito.get(3) + ","
							+ listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));

				}

			}

			if (listaDigito.size() == 7) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textField.setText(listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(2) + listaDigito.get(4)
							+ "," + listaDigito.get(listaDigito.size() - 2) + listaDigito.get(listaDigito.size() - 1));

				}

			}

			if (listaDigito.size() == 8) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textField.setText(listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(2) + listaDigito.get(3)
							+ listaDigito.get(5) + "," + listaDigito.get(listaDigito.size() - 2)
							+ listaDigito.get(listaDigito.size() - 1));

				}
			}

			if (listaDigito.size() == 9) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textField.setText(listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(2) + listaDigito.get(3)
							+ listaDigito.get(4) +listaDigito.get(6)+ "," + listaDigito.get(listaDigito.size() - 2)
							+ listaDigito.get(listaDigito.size() - 1));

				}
			}
			
			if (listaDigito.size() == 10) {
				if (doEquals(listaDigito.get(listaDigito.size() - 1))
						&& doEquals(listaDigito.get(listaDigito.size() - 2))
						&& doEquals(listaDigito.get(listaDigito.size() - 3))) {

					textField.setText(listaDigito.get(0) + listaDigito.get(1) + listaDigito.get(2) + listaDigito.get(3)
							+ listaDigito.get(4) +listaDigito.get(5)+listaDigito.get(7)+ "," + listaDigito.get(listaDigito.size() - 2)
							+ listaDigito.get(listaDigito.size() - 1));

				}
			}

			// ...
			System.out.println("Text changed from " + oldText + " to " + newText);
		});
	}

	public boolean doEquals(String it) {
		String s=it.replace(".", "");
		int i = Integer.parseInt(s);
		if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 6 || i == 7 || i == 8
				|| i == 9) {
			return true;
		}
		return false;
	}
}
