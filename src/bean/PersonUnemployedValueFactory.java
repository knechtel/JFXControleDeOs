package bean;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class PersonUnemployedValueFactory implements Callback, ObservableValue {
    
    public ObservableValue call(TableColumn.CellDataFeatures param) {
        Person person = new Person();
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().setValue(person.isUnemployed());
        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
            person.setUnemployed(new_val);
        });
        return new SimpleObjectProperty(checkBox);
    }

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(ChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(ChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object call(Object param) {
		// TODO Auto-generated method stub
		return null;
	}
}