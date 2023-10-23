package application.services.repository.commons;

import application.services.repository.IRepository;

public interface IRepositoryFactory<TItem> {
    public IRepository<TItem> getFactory();
}
