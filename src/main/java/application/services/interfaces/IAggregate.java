package application.services.interfaces;

public interface IAggregate<TItem> {
    public Iterator<TItem> createIterator();
}
