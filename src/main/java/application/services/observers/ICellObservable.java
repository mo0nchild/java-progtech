package application.services.observers;

import application.models.FieldModel;

public interface ICellObservable {
    public void addListener(ICellObserver listener);
    public void removeListener(ICellObserver listener);

    public void notifyAllListeners(FieldModel.FieldCell cell);
}
