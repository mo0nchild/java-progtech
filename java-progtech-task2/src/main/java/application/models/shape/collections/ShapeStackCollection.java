package application.models.shape.collections;

import application.models.shape.Shape;
import application.services.repository.commons.IRepositoryFactory;
import javafx.beans.InvalidationListener;

import java.util.ArrayList;
import java.util.Collection;

public class ShapeStackCollection implements IShapeStackCollection {
    private final ArrayList<InvalidationListener> listeners = new ArrayList<>();
    private final IRepositoryFactory<Shape> repositoryFactory;

    public ShapeStackCollection(IRepositoryFactory<Shape> factory) {
        super();
        this.repositoryFactory = factory;
    }
    private void notifyAllListeners() {
        for (var listener : this.listeners) listener.invalidated(this);
    }
    @Override
    public void putItem(Shape item) throws Exception {
        try (var repository = this.repositoryFactory.getFactory()) {
            repository.putItem(item);
        }
        this.notifyAllListeners();
    }

    @Override
    public void putAll(Collection<Shape> item) throws Exception {
        try (var repository = this.repositoryFactory.getFactory()) {
            repository.putAll(item);
        }
        this.notifyAllListeners();
    }

    @Override
    public Shape getItem() throws Exception {
        try (var repository = this.repositoryFactory.getFactory()) {
            return repository.getItem(repository.getCount() - 1);
        }
    }
    @Override
    public Collection<Shape> getAll() throws Exception {
        try (var repository = this.repositoryFactory.getFactory()) {
            return repository.getAll();
        }
    }
    @Override
    public void removeItem() throws Exception {
        try (var repository = this.repositoryFactory.getFactory()) {
            repository.removeItem(repository.getCount() - 1);
        }
        this.notifyAllListeners();
    }
    @Override
    public void removeAll() throws Exception {
        try (var repository = this.repositoryFactory.getFactory()) {
            repository.removeAll();
        }
        this.notifyAllListeners();
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        this.listeners.add(invalidationListener);
    }
    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        this.listeners.remove(invalidationListener);
    }
}
