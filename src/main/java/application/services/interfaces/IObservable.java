package application.services.interfaces;

public interface IObservable {
    public void attach(IObserver subscriber);
    public void detach(IObserver subscriber);
    public void notifyAllObserver();
}
