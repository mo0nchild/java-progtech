package application.services;

import application.models.Shape;

public interface IRepository<TItem> {
    public Iterable<Shape> getAll();
    public void putItem(TItem shape) throws RepositoryException;
    public TItem getItem();

    public void saveState() throws RepositoryException;
}
