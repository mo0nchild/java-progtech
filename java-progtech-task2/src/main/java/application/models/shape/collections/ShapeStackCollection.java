package application.models.shape.collections;

import application.models.shape.Shape;
import application.services.repository.commons.IRepositoryFactory;
import javafx.beans.InvalidationListener;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class ShapeStackCollection implements IShapeCollection {
    private final ArrayList<InvalidationListener> listeners = new ArrayList<>();
    private final IRepositoryFactory<Shape> repositoryFactory;

    public ShapeCollection(IRepositoryFactory<Shape> factory) {
        super();
        this.repositoryFactory = factory;
    }

    @Override
    public void putItem(Shape item) {
        try (var repository = this.repositoryFactory.getFactory()) {
            repository.
        }
    }

    @Override
    public Shape getItem() {
        return null;
    }

    @Override
    public Collection<Shape> getAll() {
        return null;
    }

    @Override
    public void removeItem() {

    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
