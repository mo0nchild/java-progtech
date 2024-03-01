package application.services.observers;

import application.models.FieldModel;

public interface ICellObserver {
    public void handler(FieldModel.FieldCell fieldCell);
}
