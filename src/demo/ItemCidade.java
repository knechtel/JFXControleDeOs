package demo;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemCidade {
	public SimpleLongProperty id = new SimpleLongProperty();
	public SimpleStringProperty name = new SimpleStringProperty();	
	
	
	public ItemCidade() {
	}

	public Long getId() {
		return id.get();
	}

	public String getName() {
		return name.get();
	}
}
