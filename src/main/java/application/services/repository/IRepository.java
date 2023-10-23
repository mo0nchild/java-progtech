package application.services.repository;

import java.io.Closeable;
import java.util.Collection;

public interface IRepository<TItem> extends Closeable {
    public Collection<TItem> getAll() throws RepositoryException;
    public void putItem(TItem item) throws RepositoryException;

    public int getCount() throws RepositoryException;
    public TItem getItem(int index) throws RepositoryException;
    public void putAll(Collection<TItem> items) throws RepositoryException;

    public void removeAll() throws RepositoryException;
    public void removeItem(int index) throws RepositoryException;
}
