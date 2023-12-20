package application.components.builder.services.interfaces;

public interface IAggregate<TItem> {
    public Iterator<TItem> createIterator();
}
