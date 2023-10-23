package application.models.shape.collections;

import application.models.shape.Shape;
import javafx.beans.Observable;

import java.util.Collection;

public interface IShapeStackCollection extends Observable {
    public void putItem(Shape item) throws Exception;
    public void putAll(Collection<Shape> item) throws Exception;

    public Shape getItem() throws Exception;
    public Collection<Shape> getAll() throws Exception;
    public void removeItem() throws Exception;
    public void removeAll() throws Exception;
}
