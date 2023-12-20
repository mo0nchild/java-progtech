package application.services;

public interface IObserver<TSubject> {
    public final class EventArgs extends Object {

    }
    public abstract void update(Object sender, EventArgs);
}
