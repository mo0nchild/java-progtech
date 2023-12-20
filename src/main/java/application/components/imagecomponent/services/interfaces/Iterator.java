package application.components.builder.services.interfaces;

public interface Iterator<TItem> {
    public boolean hasNext();
    public boolean hasPrevious();
    public TItem next();
    public TItem preview();
}
