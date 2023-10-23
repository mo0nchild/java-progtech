package application.models.shape.collections;

import application.models.shape.Shape;
import javafx.beans.Observable;

import java.util.Collection;

public interface IShapeCollection extends Observable {
    public void putItem(Shape item);
    public Shape getItem();
    public Collection<Shape> getAll();
    public void removeItem();
}
