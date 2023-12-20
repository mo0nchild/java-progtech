package application.components.imagecomponent.services.interfaces;

public interface IAggregate<TItem> {
    public Iterator<TItem> createIterator();
}
