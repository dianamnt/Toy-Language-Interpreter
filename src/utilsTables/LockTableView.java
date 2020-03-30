package utilsTables;

import javafx.beans.property.SimpleIntegerProperty;

public class LockTableView {
    private SimpleIntegerProperty location, value, ba;

    public LockTableView(Integer address, Integer value) {
        this.location = new SimpleIntegerProperty(address);
        this.value = new SimpleIntegerProperty(value);

    }


    public SimpleIntegerProperty baProperty() {
        return ba;
    }

    public void setLocation(int address) {
        this.location.set(address);
    }

    public void setValue(int value) {
        this.value.set(value);
    }



    public SimpleIntegerProperty locationProperty() {
        return location;
    }

    public int getValue() {
        return value.get();
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }
}
