package demo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
	public SimpleLongProperty id = new SimpleLongProperty();
	public SimpleStringProperty name = new SimpleStringProperty();
	public SimpleStringProperty modelo = new SimpleStringProperty();
	public SimpleStringProperty serial = new SimpleStringProperty();
	public SimpleStringProperty marca = new SimpleStringProperty();
	public SimpleBooleanProperty pronto = new SimpleBooleanProperty();

	public SimpleBooleanProperty autorizado = new SimpleBooleanProperty();

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

	public String getSerial() {
		return serial.get();
	}

	public String getMarca() {
		return marca.get();
	}

}